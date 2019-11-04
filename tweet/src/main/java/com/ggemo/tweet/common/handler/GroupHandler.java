package com.ggemo.tweet.common.handler;

import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.cqclient.QqMq;
import com.ggemo.tweet.pojo.dos.Tweet2qqDO;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.Status;

import java.util.Map;
import java.util.Set;

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
    public void handle(Status status) {
        long userId = status.getUser().getId();
        Set<Object> followerQqSet = redisUtil.sGet("tweet_qq_" + userId);
        for (Object o : followerQqSet) {
            Integer groupId = (Integer) o;
            Map<Object, Object> followerInfo = redisUtil.hget(String.format("tweet_qq_%s_%d", userId, groupId));
            Tweet2qqDO tweet2qqDO = Tweet2qqDO.fromMap(followerInfo);

            sendMsg(tweet2qqDO.getQqGroupId(), tweet2qqDO.getFormat(), tweet2qqDO.getTweetNickName(), status.getSource(), status.getText(),"");
        }
    }
}
