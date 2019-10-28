package com.ggemo.tweet.tweet;

import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.pojo.dos.Tweet2qqDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.User;

import java.util.Map;
import java.util.Set;

@Component
public class StatusListener implements twitter4j.StatusListener {
    Set<Long>  follows;

    @Autowired
    RedisUtil redisUtil;

    public StatusListener(Set<Long> followsSet) {
        super();
        this.follows = followsSet;
    }

    @Override
    public void onStatus(Status status) {
        User user = status.getUser();
        long userId = user.getId();
        if(!follows.contains(userId)){
            return;
        }
        Set<Object> followerQqSet = redisUtil.sGet("tweet_qq_" + userId);
        for (Object o : followerQqSet) {
            Integer groupId = (Integer) o;
            Map<Object, Object> followerInfo = redisUtil.hget(String.format("tweet_qq_%s_%d", userId, groupId));
            Tweet2qqDO tweet2qqDO = Tweet2qqDO.fromMap(followerInfo);

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
