package com.ggemo.tweet;

import com.ggemo.tweet.common.filter.Filter;
import com.ggemo.tweet.common.filter.FollowFilter;
import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.tweet.MyStatusListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StartTweet {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    MyStatusListener myStatusListener;

    @Autowired
    FollowFilter followFilter;

    public void listen() {
        Set<Object> follows = redisUtil.sGet("tweets");
        Set<Long> followsSet = follows.stream().map((x)->Long.parseLong((String)x)).collect(Collectors.toSet());
        followFilter.setFollows(followsSet);
        myStatusListener.addFilter(followFilter);

        long[] followArray = new long[followsSet.size()];
        int i = 0;
        for (Long aLong : followsSet) {
            followArray[i] = aLong;
            i += 1;
        }
        TwitterStream stream = new TwitterStreamFactory().getInstance();

        FilterQuery filterQuery = new FilterQuery();
        filterQuery.follow(followArray);
        stream = stream.addListener(myStatusListener);
        stream.filter(filterQuery);
    }

}
