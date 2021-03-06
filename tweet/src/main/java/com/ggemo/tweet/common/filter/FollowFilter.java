package com.ggemo.tweet.common.filter;

import com.ggemo.tweet.common.StatusWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.Set;

@Slf4j
@Component
public class FollowFilter implements Filter {
    private static final String NAME = "FollowFilter";
    private Set<Long> follows;

    public void setFollows(Set<Long> follows) {
        this.follows = follows;
    }

    @Override
    public boolean filter(StatusWrapper statuswrapper) {
        Status status = statuswrapper.getStatus();
        if(!follows.contains(status.getUser().getId())){
            return false;
        }
        log.info("收到推特: userName: " + status.getUser().getName());
        return true;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
