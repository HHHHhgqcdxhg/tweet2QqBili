package com.ggemo.tweet.tweet;

import com.ggemo.tweet.common.filter.Filter;
import com.ggemo.tweet.common.handler.Handler;
import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.cqclient.QqMq;
import com.ggemo.tweet.pojo.dos.Tweet2qqDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class MyStatusListener implements StatusListener {
    private List<Filter> filters;

    private List<Handler> handlers;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private QqMq qqMq;

    public void addFilter(Filter filter){
        this.filters.add(filter);
    }

    public void addHandler(Handler handler){
        this.handlers.add(handler);
    }


    @PostConstruct
    public void init(){
        this.filters = new ArrayList<>();
        this.handlers = new ArrayList<>();
    }

    @Override
    public void onStatus(Status status) {
        User user = status.getUser();
        long userId = user.getId();
        for (Filter filter : filters) {
            if(!filter.filter(status)){
                return;
            }
        }

        for (Handler handler : handlers) {
            handler.handle(status);
        }
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int i) {

    }

    @Override
    public void onScrubGeo(long l, long l1) {

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {

    }

    @Override
    public void onException(Exception e) {

    }
}
