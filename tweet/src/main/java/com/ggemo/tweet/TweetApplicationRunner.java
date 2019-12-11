package com.ggemo.tweet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Order(value = 2)
@Component
class TweetApplicationRunner implements org.springframework.boot.ApplicationRunner {

    @Autowired
    StartTweet startTweet;

    @Override
    public void run(ApplicationArguments args){
        log.info("run TweetApplicationRunner");
        ExecutorService pool = Executors.newSingleThreadExecutor((r) -> {
            Thread t = new Thread(r);
            t.setName("监听推特的线程");
            return t;
        });

        pool.execute(() -> {
            startTweet.listen();
        });
    }
}