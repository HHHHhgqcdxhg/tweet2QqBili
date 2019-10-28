package com.ggemo.tweet;

import com.ggemo.tweet.refreshredis.LoadFromMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
class TweetApplicationRunner implements org.springframework.boot.ApplicationRunner {
    @Autowired
    LoadFromMysql loadFromMysql;

    @Autowired
    StartTweet startTweet;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startTweet.run();
    }


}