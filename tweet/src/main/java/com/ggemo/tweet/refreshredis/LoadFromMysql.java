package com.ggemo.tweet.refreshredis;

import com.alibaba.fastjson.JSON;
import com.ggemo.tweet.common.RedisKeysEnum;
import com.ggemo.tweet.common.util.RedisUtil;
import com.ggemo.tweet.pojo.dao.BiliAccountDAO;
import com.ggemo.tweet.pojo.dao.Tweet2biliDAO;
import com.ggemo.tweet.pojo.dao.Tweet2qqDAO;
import com.ggemo.tweet.pojo.dao.TweetInfoDAO;
import com.ggemo.tweet.pojo.dos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoadFromMysql {
    @Autowired
    TweetInfoDAO tweetInfoDAO;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    Tweet2qqDAO tweet2qqDAO;

    @Autowired
    BiliAccountDAO biliAccountDAO;

    @Autowired
    Tweet2biliDAO tweet2biliDAO;

    public void loadFollowTweetList() {
        redisUtil.multi();
        redisUtil.del(RedisKeysEnum.TWEETS.val());
        List<TweetInfoDO> tweetInfos = tweetInfoDAO.selectByParam(new TweetInfoParam());
        List<String> tweets = tweetInfos.stream().map(TweetInfoDO::getTweetId).collect(Collectors.toList());
        String[] tweetsArray = new String[tweets.size()];
        tweetsArray = tweets.toArray(tweetsArray);
        redisUtil.sSet(RedisKeysEnum.TWEETS.val(), (Object[]) tweetsArray);
        redisUtil.exec();
    }

    public void loadFollowQqGroups() {
        redisUtil.multi();
        redisUtil.delete(RedisKeysEnum.TWEET_QQ_ALL.val());
        Tweet2qqParam tweet2qqParam = new Tweet2qqParam();
        tweet2qqParam.createCriteria().andFollowEqualTo(1);
        List<Tweet2qqDO> tweet2qqDOS = tweet2qqDAO.selectByParam(tweet2qqParam);
        for (Tweet2qqDO tweet2qqDO : tweet2qqDOS) {
            redisUtil.hSet(String.format(RedisKeysEnum.TWEET_QQ_TWEETID_GROUPID.val(), tweet2qqDO.getTweetId(), tweet2qqDO.getQqGroupId()), tweet2qqDO.toMap());
            redisUtil.sSet(String.format(RedisKeysEnum.TWEET_QQ_TWEETID.val(), tweet2qqDO.getTweetId()), tweet2qqDO.getQqGroupId());
        }
        redisUtil.exec();
    }

    public void loadBiliAccount(){
        redisUtil.multi();
        redisUtil.delete(RedisKeysEnum.BILI_COOKIE_All.val());
        List<BiliAccountDO> biliAccountDOS = biliAccountDAO.selectByParamWithBLOBs(new BiliAccountParam());
        for (BiliAccountDO biliAccountDO : biliAccountDOS) {
            String cookie = biliAccountDO.getCookie();
            List<Map<String, Object>> cookieList = (List<Map<String, Object>>) JSON.parse(cookie);
            StringBuilder realCookies = new StringBuilder();
            for (Map<String, Object> map : cookieList) {
                realCookies.append(((String) map.get("name")).strip()).append("=").append(((String) map.get("value")).strip()).append(";");
            }
            redisUtil.set(String.format(RedisKeysEnum.BILI_COOKIE_BILIID.val(), biliAccountDO.getBiliId()), realCookies.toString());
        }
        redisUtil.exec();
    }

    public void loadFollowBili(){
        redisUtil.multi();
        redisUtil.delete(RedisKeysEnum.TWEET_BILI_ALL.val());
        Tweet2biliParam param = new Tweet2biliParam();
        param.createCriteria().andFollowEqualTo(1);
        List<Tweet2biliDO> tweet2biliDOS = tweet2biliDAO.selectByParam(param);
        for (Tweet2biliDO tweet2biliDO : tweet2biliDOS) {
            redisUtil.hSet(String.format(RedisKeysEnum.TWEET_BILI_TWEETID_BILIID.val(), tweet2biliDO.getTweetId(), tweet2biliDO.getBiliId()), tweet2biliDO.toMap());
            redisUtil.sSet(String.format(RedisKeysEnum.TWEET_BILI_TWEETID.val(), tweet2biliDO.getTweetId()), tweet2biliDO.getBiliId());
        }
        redisUtil.exec();
    }

    public void loadAll() {
        redisUtil.multi();
        try {
            redisUtil.flushAll();
            loadFollowTweetList();
            loadFollowQqGroups();
            loadBiliAccount();
            loadFollowBili();
        }finally {
            redisUtil.exec();
        }
    }
}
