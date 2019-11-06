package com.ggemo.tweet;

import com.ggemo.tweet.cqclient.QqMq;
import com.ggemo.tweet.cqclient.QqRequestService;
import com.ggemo.tweet.refreshredis.LoadFromMysql;
import com.ggemo.tweet.translate.Translate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    @Qualifier("BaiduTranslate")
    Translate translate;

    @Override
    public void run(ApplicationArguments args) {
        log.info("run");
        String text = "【公式四コマ】\n" +
                "『アズールレーン びそくぜんしんっ！』39話・②\n" +
                "「ニーミ、着せられるパターンが多い気がするです」\n" +
                "「断ってもいいのに、結局着てしまうのね＞＜」\n" +
                "「お人好しだから断れない……かも」\n" +
                "次回掲載は11月5日（火）になります！";
        String res = translate.translate(text);
        log.info(res);
    }
}