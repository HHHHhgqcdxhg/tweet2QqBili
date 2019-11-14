package com.ggemo.tweet.common.handler;


import com.alibaba.fastjson.JSONException;
import com.ggemo.bilidynamicclient.BiliDynamicClient;
import com.ggemo.bilidynamicclient.exception.BiliClientException;
import com.ggemo.bilidynamicclient.response.impl.CreateResponse;
import com.ggemo.bilidynamicclient.response.impl.CreateWithImgResponse;
import com.ggemo.tweet.common.RedisKeysEnum;
import com.ggemo.tweet.common.StatusWrapper;
import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.pojo.dos.Tweet2biliDO;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Client;
import twitter4j.Status;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SendBiliHandler implements Handler {
    private static final String NAME = "SendBiliHandler";

    @Autowired
    RedisUtil redisUtil;

    public BiliDynamicClient biliClient;

    @PostConstruct
    public void init() {
        this.biliClient = new BiliDynamicClient();
    }

    @Override
    public void handle(StatusWrapper statusWrapper) {
        Status status = statusWrapper.getStatus();
        long userId = status.getUser().getId();
        Set<Object> followerSet = redisUtil.sGet(String.format(RedisKeysEnum.TWEET_BILI_TWEETID.val(), userId));
        for (Object o : followerSet) {
            Map<Object, Object> followerInfo = redisUtil.hget(String.format(RedisKeysEnum.TWEET_BILI_TWEETID_BILIID.val(), userId, (Integer)o));
            Tweet2biliDO tweet2biliDO = Tweet2biliDO.fromMap(followerInfo);
            Long biliId = tweet2biliDO.getBiliId();
            String biliCookie = (String) redisUtil.get(String.format(RedisKeysEnum.BILI_COOKIE_BILIID.val(), biliId));
            log.info("biliCookie: " + biliCookie);
            String dynamicId = null;
            if (statusWrapper.getImages().size() > 0) {
                CreateWithImgResponse res = null;
                List<File> imgFiles = statusWrapper.getImages().stream().map((img) -> new File(img.getLocalPath())).collect(Collectors.toList());
                try {
                    dynamicId = create(imgFiles, status, biliCookie);
                } catch (BiliClientException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    dynamicId = create(status, biliCookie);
                } catch (BiliClientException e) {
                    e.printStackTrace();
                }
            }
            if (tweet2biliDO.getTrans() == 1) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error(e.toString() + e.getMessage());
                }
                try {
                    biliClient.sendReply(dynamicId, statusWrapper.getTransed(), biliCookie);
                } catch (IOException | JSONException e ) {
                    e.printStackTrace();
                    log.error(e.toString() + e.getMessage());
                }
            }
        }
    }

    private String create(Status status, String biliCookie) throws BiliClientException {
        CreateResponse res;
        while (true) {
            try {
                res = biliClient.create(status.getText(), biliCookie);
                log.info("发送bili动态res:" + res.toString());
                break;
            } catch (IOException | BiliClientException e) {
                e.printStackTrace();
            }
        }
        return res.getData().getDynamicIdStr();
    }

    private String create(List<File> imgFiles, Status status, String biliCookie) throws BiliClientException {
        CreateWithImgResponse res;
        while (true) {
            try {
                res = biliClient.create(imgFiles, status.getText(), biliCookie);
                break;
            } catch (IOException | BiliClientException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return res.getData().getDynamicIdStr();
    }

    @Override
    public String getName() {
        return NAME;
    }
}
