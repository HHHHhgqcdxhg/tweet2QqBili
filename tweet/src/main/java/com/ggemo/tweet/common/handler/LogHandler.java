package com.ggemo.tweet.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Status;

@Slf4j
@Component
public class LogHandler implements Handler {
    @Override
    public void handle(Status status) {
        log.info("收到来自: " + status.getUser().getName() + "的新推特:\n" + status.getText());
    }
}
