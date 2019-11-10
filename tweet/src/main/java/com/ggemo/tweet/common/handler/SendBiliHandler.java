package com.ggemo.tweet.common.handler;

import com.ggemo.bilidynamicclient.BiliDynamicClient;
import com.ggemo.tweet.common.RedisKeysEnum;
import com.ggemo.tweet.common.StatusWrapper;
import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.pojo.dos.Tweet2biliDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class SendBiliHandler implements Handler {
    @Autowired
    RedisUtil redisUtil;

    private BiliDynamicClient biliClient;

    @PostConstruct
    public void init(){
        this.biliClient = new BiliDynamicClient();
    }

    @Override
    public void handle(StatusWrapper statusWrapper) {
        Status status = statusWrapper.getStatus();
        long userId = status.getUser().getId();
        Set<Object> followerSet = redisUtil.sGet(String.format(RedisKeysEnum.TWEET_BILI_TWEETID.val(), userId));
        for (Object o : followerSet) {
            Map<Object, Object> followerInfo = redisUtil.hget(String.format(RedisKeysEnum.TWEET_BILI_TWEETID_BILIID.val(), userId, (Long) o));
            Tweet2biliDO tweet2biliDO = Tweet2biliDO.fromMap(followerInfo);

        }
    }
}
