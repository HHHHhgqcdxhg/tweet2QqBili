package com.ggemo.tweet.common.handler;

import com.ggemo.tweet.common.RedisKeysEnum;
import com.ggemo.tweet.common.StatusWrapper;
import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.cqclient.QqMq;
import com.ggemo.tweet.pojo.dos.Tweet2qqDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class GroupHandler implements Handler {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    QqMq qqMq;

    private void sendMsg(int qqGroup, String format, String tweetNickName, String url, String content, String trans) {
        for (String s : format.split("\\|fgf\\|")) {
            s = s.replace("{tweet_nick_name}", tweetNickName)
                    .replace("{url}", url)
                    .replace("{content}", content)
                    .replace("trans", trans);
            qqMq.put(qqMq.new Task(qqGroup, s));
        }
    }

    @Override
    public void handle(StatusWrapper statusWrapper) {
        Status status = statusWrapper.getStatus();
        long userId = status.getUser().getId();
        Set<Object> followerQqSet = redisUtil.sGet(String.format(RedisKeysEnum.TWEET_QQ_TWEETID.val(), userId));
        for (Object o : followerQqSet) {
            Long groupId = (Long) o;
            Map<Object, Object> followerInfo = redisUtil.hget(String.format(RedisKeysEnum.TWEET_QQ_TWEETID_GROUPID.val(), userId, groupId));
            Tweet2qqDO tweet2qqDO = Tweet2qqDO.fromMap(followerInfo);
            sendMsg(tweet2qqDO.getQqGroupId().intValue(), tweet2qqDO.getFormat(), tweet2qqDO.getTweetNickName(), status.getSource(), status.getText(),statusWrapper.getTransed());
        }
    }
}
