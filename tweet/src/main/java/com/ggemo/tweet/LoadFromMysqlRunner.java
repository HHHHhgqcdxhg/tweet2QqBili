package com.ggemo.tweet;

import com.ggemo.tweet.refreshredis.LoadFromMysql;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(value = 1)
@Component
public class LoadFromMysqlRunner implements org.springframework.boot.ApplicationRunner {
    @Autowired
    LoadFromMysql loadFromMysql;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        log.info("run LoadFromMysqlRunner");
//        loadFromMysql.loadAll();
    }
}
