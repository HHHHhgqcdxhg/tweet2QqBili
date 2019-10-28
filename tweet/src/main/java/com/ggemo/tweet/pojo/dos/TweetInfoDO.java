package com.ggemo.tweet.pojo.dos;

import java.io.Serializable;

/**
 *
 * @author 清纯的小黄瓜
 */
public class TweetInfoDO implements Serializable {
    /**
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     *
     * @mbg.generated
     */
    private String tweetId;

    /**
     * Database Column Remarks:
     *   推主名, @后的那堆东西
     *
     *
     * @mbg.generated
     */
    private String tweetName;

    /**
     * Database Column Remarks:
     *   备注名, 用于辨识出时哪个推主
     *
     *
     * @mbg.generated
     */
    private String backUp;

    /**
     *
     * @return the value of tweet_info.tweet_id
     *
     * @mbg.generated
     */
    public String getTweetId() {
        return tweetId;
    }

    /**
     *
     * @param tweetId the value for tweet_info.tweet_id
     *
     * @mbg.generated
     */
    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    /**
     *
     * @return the value of tweet_info.tweet_name
     *
     * @mbg.generated
     */
    public String getTweetName() {
        return tweetName;
    }

    /**
     *
     * @param tweetName the value for tweet_info.tweet_name
     *
     * @mbg.generated
     */
    public void setTweetName(String tweetName) {
        this.tweetName = tweetName;
    }

    /**
     *
     * @return the value of tweet_info.back_up
     *
     * @mbg.generated
     */
    public String getBackUp() {
        return backUp;
    }

    /**
     *
     * @param backUp the value for tweet_info.back_up
     *
     * @mbg.generated
     */
    public void setBackUp(String backUp) {
        this.backUp = backUp;
    }

    /**
     * @return
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", tweetId=").append(tweetId);
        sb.append(", tweetName=").append(tweetName);
        sb.append(", backUp=").append(backUp);
        sb.append("]");
        return sb.toString();
    }
}