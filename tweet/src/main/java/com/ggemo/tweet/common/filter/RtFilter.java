package com.ggemo.tweet.common.filter;

import com.ggemo.tweet.common.StatusWrapper;
import org.springframework.stereotype.Component;
import twitter4j.Status;

@Component
public class RtFilter implements Filter {
    private static final String NAME = "RtFilter";
    @Override
    public boolean filter(StatusWrapper statusWrapper) {
        Status status = statusWrapper.getStatus();
        return !status.isRetweeted() && status.getRetweetedStatus() == null;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
