package com.ggemo.tweet.common.prehandler;

import com.ggemo.tweet.common.StatusWrapper;

public interface PreHandler {
    StatusWrapper handle(StatusWrapper statusWrapper);

    String getName();
}
