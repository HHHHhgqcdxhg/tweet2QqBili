package com.ggemo.tweet;

import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.tweet.StatusListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StartTweet {
    @Autowired
    RedisUtil redisUtil;

    public void run() {
        Set<Object> follows = redisUtil.sGet("tweets");
        Set<Long> followsSet = follows.stream().map((x)->Long.parseLong((String)x)).collect(Collectors.toSet());
        String[] followsArray = new String[follows.size()];
        followsArray = follows.toArray(followsArray);

        TwitterStream stream = new TwitterStreamFactory().getInstance();
        stream.addListener(new StatusListener(followsSet));
        FilterQuery filterQuery = new FilterQuery(followsArray);
        stream.filter(filterQuery);
        stream.sample();
    }

}
