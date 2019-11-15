package com.ggemo.tweet.common.handler;

import com.ggemo.tweet.common.RedisKeysEnum;
import com.ggemo.tweet.common.StatusWrapper;
import com.ggemo.tweet.common.util.Image;
import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.cqclient.QqMq;
import com.ggemo.tweet.pojo.dos.Tweet2qqDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class GroupHandler implements Handler {
    private static final String NAME = "GroupHandler";

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    QqMq qqMq;

    private String sendMsg(int qqGroup, String format, String tweetNickName, String url, String content, String trans, List<Image> images) {
        StringBuilder contentBuilder = new StringBuilder(content);
        for (Image image : images){
            contentBuilder.append(String.format("[CQ:image,file=%s]", image.getPublicUrl()));
        }
        content = contentBuilder.toString();
        StringBuilder sb = new StringBuilder();
        for (String s : format.split("\\|fgf\\|")) {
            s = s.replace("{tweet_nick_name}", tweetNickName)
                    .replace("{url}", url)
                    .replace("{content}", content)
                    .replace("{trans}", trans);
            qqMq.put(qqMq.new Task(qqGroup, s));
            sb.append(s).append("|fgf|");
        }
        return sb.toString();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void handle(StatusWrapper statusWrapper) {
        Status status = statusWrapper.getStatus();
        long userId = status.getUser().getId();
        Set<Object> followerQqSet = redisUtil.sGet(String.format(RedisKeysEnum.TWEET_QQ_TWEETID.val(), String.valueOf(userId)));
        for (Object o : followerQqSet) {
            Integer groupId = (Integer) o;
            Map<Object, Object> followerInfo = redisUtil.hget(String.format(RedisKeysEnum.TWEET_QQ_TWEETID_GROUPID.val(), userId, groupId));
            Tweet2qqDO tweet2qqDO = Tweet2qqDO.fromMap(followerInfo);
            String msg = sendMsg(tweet2qqDO.getQqGroupId().intValue(), tweet2qqDO.getFormat(), tweet2qqDO.getTweetNickName(), String.format("https://twitter.com/%s/status/%d", status.getUser().getScreenName(), status.getId()), statusWrapper.getText(),statusWrapper.getTransed(), statusWrapper.getImages());
            log.info(String.format("向关注了 %s 的群 %d 发送消息: %s", status.getUser().getName(), tweet2qqDO.getQqGroupId(), msg));
        }
    }
}
