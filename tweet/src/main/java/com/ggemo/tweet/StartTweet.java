package com.ggemo.tweet;

import com.ggemo.tweet.common.RedisKeysEnum;
import com.ggemo.tweet.common.filter.Filter;
import com.ggemo.tweet.common.filter.FollowFilter;
import com.ggemo.tweet.common.filter.RtFilter;
import com.ggemo.tweet.common.handler.GroupHandler;
import com.ggemo.tweet.common.handler.LogHandler;
import com.ggemo.tweet.common.handler.SendBiliHandler;
import com.ggemo.tweet.common.prehandler.DownloadImgPreHandler;
import com.ggemo.tweet.common.prehandler.TagsPreHandler;
import com.ggemo.tweet.common.prehandler.TextRemoveImgUrlPreHandler;
import com.ggemo.tweet.common.prehandler.TranslatePreHandler;
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

    @Autowired
    RtFilter rtFilter;

    @Autowired
    GroupHandler groupHandler;

    @Autowired
    LogHandler logHandler;

    @Autowired
    SendBiliHandler sendBiliHandler;

    @Autowired
    DownloadImgPreHandler downloadImgPreHandler;

    @Autowired
    TextRemoveImgUrlPreHandler textRemoveImgUrlPreHandler;

    @Autowired
    TranslatePreHandler translatePreHandler;

    @Autowired
    TagsPreHandler tagsPreHandler;

    public void listen() {
        Set<Object> follows = redisUtil.sGet(RedisKeysEnum.TWEETS.val());
        Set<Long> followsSet = follows.stream().map((x)->Long.parseLong((String)x)).collect(Collectors.toSet());
        followFilter.setFollows(followsSet);
        myStatusListener.addFilter(followFilter);
        myStatusListener.addFilter(rtFilter);

        myStatusListener.addPreHandler(downloadImgPreHandler);
        myStatusListener.addPreHandler(textRemoveImgUrlPreHandler);
        myStatusListener.addPreHandler(translatePreHandler);
        myStatusListener.addPreHandler(tagsPreHandler);

        myStatusListener.addHandler(logHandler);
        myStatusListener.addHandler(groupHandler);
        myStatusListener.addHandler(sendBiliHandler);

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
