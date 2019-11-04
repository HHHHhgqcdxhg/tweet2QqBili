package com.ggemo.tweet.common.filter;

import twitter4j.Status;

public interface Filter {
    boolean filter(Status status);
}

