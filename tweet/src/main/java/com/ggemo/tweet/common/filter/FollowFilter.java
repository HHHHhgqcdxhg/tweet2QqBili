package com.ggemo.tweet.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.Set;

@Slf4j
@Component
public class FollowFilter implements Filter {
    private Set<Long> follows;

    public void setFollows(Set<Long> follows) {
        this.follows = follows;
    }

    @Override
    public boolean filter(Status status) {
        if(!follows.contains(status.getUser().getId())){
            log.info("FollowFilter未通过! userName: " + status.getUser().getName());
            return false;
        }
        return true;
    }
}
