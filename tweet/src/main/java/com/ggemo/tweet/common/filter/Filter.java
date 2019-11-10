package com.ggemo.tweet.common.filter;

import com.ggemo.tweet.common.StatusWrapper;
import twitter4j.Status;

public interface Filter {
    boolean filter(StatusWrapper statusWrapper);
}

