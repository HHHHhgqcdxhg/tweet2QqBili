package com.ggemo.tweet.refreshredis;

import com.alibaba.fastjson.JSON;
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
        redisUtil.del("tweets");
        List<TweetInfoDO> tweetInfos = tweetInfoDAO.selectByParam(new TweetInfoParam());
        List<String> tweets = tweetInfos.stream().map(TweetInfoDO::getTweetId).collect(Collectors.toList());
        String[] tweetsArray = new String[tweets.size()];
        tweetsArray = tweets.toArray(tweetsArray);
        redisUtil.sSet("tweets", (Object[]) tweetsArray);
        redisUtil.exec();
    }

    public void loadFollowQqGroups() {
        redisUtil.multi();
        redisUtil.delete("tweet_qq_*");
        Tweet2qqParam tweet2qqParam = new Tweet2qqParam();
        tweet2qqParam.createCriteria().andFollowEqualTo(1);
        List<Tweet2qqDO> tweet2qqDOS = tweet2qqDAO.selectByParam(tweet2qqParam);
        for (Tweet2qqDO tweet2qqDO : tweet2qqDOS) {
            redisUtil.hSet(String.format("tweet_qq_%s_%d", tweet2qqDO.getTweetId(), tweet2qqDO.getQqGroupId()), tweet2qqDO.toMap());
            redisUtil.sSet(String.format("tweet_qq_%s", tweet2qqDO.getTweetId()), tweet2qqDO.getQqGroupId());
        }
        redisUtil.exec();
    }

    public void loadBiliAccount(){
        redisUtil.multi();
        redisUtil.delete("bili_cookie_*");
        List<BiliAccountDO> biliAccountDOS = biliAccountDAO.selectByParamWithBLOBs(new BiliAccountParam());
        for (BiliAccountDO biliAccountDO : biliAccountDOS) {
            String cookie = biliAccountDO.getCookie();
            List<Map<String, Object>> cookieList = (List<Map<String, Object>>) JSON.parse(cookie);
            Map<String, Object> realCookies = new HashMap<>();
            for (Map<String, Object> map : cookieList) {
                realCookies.put((String) map.get("name"), map.get("value"));
            }
            redisUtil.hSet("bili_cookie_" + biliAccountDO.getBiliId(), realCookies);
        }
        redisUtil.exec();
    }

    public void loadFollowBili(){
        redisUtil.multi();
        redisUtil.delete("tweet_bili_*");
        Tweet2biliParam param = new Tweet2biliParam();
        param.createCriteria().andFollowEqualTo(1);
        List<Tweet2biliDO> tweet2biliDOS = tweet2biliDAO.selectByParam(param);
        for (Tweet2biliDO tweet2biliDO : tweet2biliDOS) {
            redisUtil.hSet(String.format("tweet_bili_%s_%d", tweet2biliDO.getTweetId(), tweet2biliDO.getBiliId()), tweet2biliDO.toMap());
            redisUtil.sSet(String.format("tweet_bili_%s", tweet2biliDO.getTweetId()), tweet2biliDO.getBiliId());
        }
        redisUtil.exec();
    }

    public void loadAll() {
        redisUtil.multi();
        redisUtil.flushAll();

        loadFollowTweetList();
        loadFollowQqGroups();
        loadBiliAccount();
        loadFollowBili();

        redisUtil.exec();
    }
}
