package com.ggemo.tweet.common.handler;

import com.ggemo.tweet.common.StatusWrapper;
import com.ggemo.tweet.pojo.dos.Tweet2qqDO;
import twitter4j.Status;

public interface Handler {
    void handle(StatusWrapper statusWrapper);
}
