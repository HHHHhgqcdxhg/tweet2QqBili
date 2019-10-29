package com.ggemo.tweet.tweet;

import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.cqclient.QqMq;
import com.ggemo.tweet.cqclient.QqRequest;
import com.ggemo.tweet.cqclient.QqRequestImpl;
import com.ggemo.tweet.pojo.dos.QqGroupInfoDO;
import com.ggemo.tweet.pojo.dos.Tweet2qqDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class MyStatusListener implements StatusListener {
    private Set<Long> follows;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private QqMq qqMq;

    public void setFollows(Set<Long> followsSet) {
        this.follows = followsSet;
    }

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
    public void onStatus(Status status) {
        User user = status.getUser();
        long userId = user.getId();
        if (!follows.contains(userId)) {
            return;
        }

        log.info("收到来自 " + user.getName() + " 的新推特:");

        Set<Object> followerQqSet = redisUtil.sGet("tweet_qq_" + userId);
        for (Object o : followerQqSet) {
            Integer groupId = (Integer) o;
            Map<Object, Object> followerInfo = redisUtil.hget(String.format("tweet_qq_%s_%d", userId, groupId));
            Tweet2qqDO tweet2qqDO = Tweet2qqDO.fromMap(followerInfo);

            sendMsg(groupId, tweet2qqDO.getFormat(), tweet2qqDO.getTweetNickName(), status.getSource(), status.getText(), "");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int i) {

    }

    @Override
    public void onScrubGeo(long l, long l1) {

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {

    }

    @Override
    public void onException(Exception e) {

    }
}
