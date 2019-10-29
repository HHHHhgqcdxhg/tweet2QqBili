package com.ggemo.tweet;

import com.ggemo.tweet.cqclient.QqMq;
import com.ggemo.tweet.cqclient.QqRequest;
import com.ggemo.tweet.cqclient.QqRequestService;
import com.ggemo.tweet.refreshredis.LoadFromMysql;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(value = 1)
@Component
class TweetApplicationRunner implements org.springframework.boot.ApplicationRunner {
    @Autowired
    LoadFromMysql loadFromMysql;
//
//    @Autowired
//    StartTweet startTweet;

    @Autowired
    QqMq qqMq;

    @Autowired
    QqRequestService qqRequestService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("run");
//        loadFromMysql.loadAll();
//        startTweet.listen();

//        log.info("put");
        qqRequestService.start();
       qqMq.put(qqMq.new Task(119277816, "12345sadfsadf爱上杜甫"));
       qqMq.put(qqMq.new Task(119277816, "12345sadfsadf爱上杜去去去甫"));
        log.info("putted");
    }


}