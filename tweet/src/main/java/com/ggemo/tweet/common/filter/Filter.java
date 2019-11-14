package com.ggemo.tweet.common.filter;

import com.ggemo.tweet.common.StatusWrapper;
import twitter4j.Status;

/**
 * @author 清纯的小黄瓜
 */
public interface Filter {
    boolean filter(StatusWrapper statusWrapper);
    String getName();
}

