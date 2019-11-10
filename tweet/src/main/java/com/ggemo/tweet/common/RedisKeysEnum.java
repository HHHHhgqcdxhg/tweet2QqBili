package com.ggemo.tweet.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RedisKeysEnum {
    /**
     * 所有关注的推主的id的set
     */
    TWEETS("tweets"),

    /**
     * 所有与qq相关的键
     */
    TWEET_QQ_ALL("tweet_qq_*"),

    /**
     * 通过tweetId和groupId获取groupInfo时用的键
     */
    TWEET_QQ_TWEETID_GROUPID("tweet_qq_%s_%d"),

    /**
     * 通过tweetId获取其关注者的GroupId的set时用的键
     */
    TWEET_QQ_TWEETID("tweet_qq_%s"),

    /**
     * 所有biliCookie
     */
    BILI_COOKIE_All("bili_cookie_*"),

    /**
     * 通过biliId获取其cookie时用的键
     */
    BILI_COOKIE_BILIID("bili_cookie_%d"),

    /**
     * 所有tweet bili相关的键
     */
    TWEET_BILI_ALL("tweet_bili_*"),

    /**
     * 根据tweetId和BiliId获取BiliInfo时用的键
     */
    TWEET_BILI_TWEETID_BILIID("tweet_bili_%s_%d"),

    /**
     * 通过tweetId获取其关注者的biliId的set时用的键
     */
    TWEET_BILI_TWEETID("tweet_bili_%s"),
    ;

    private String keyName;

    public String val(){
        return this.keyName;
    }
}
