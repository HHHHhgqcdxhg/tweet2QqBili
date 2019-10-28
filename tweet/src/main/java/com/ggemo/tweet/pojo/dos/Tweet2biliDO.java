package com.ggemo.tweet.pojo.dos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 清纯的小黄瓜
 */
public class Tweet2biliDO implements Serializable {
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
     *   转推至 的bilibili账号id
     *
     *
     * @mbg.generated
     */
    private Integer biliId;

    /**
     *
     *
     * @mbg.generated
     */
    private Integer follow;

    /**
     * Database Column Remarks:
     *   boolean, 是否翻译
     *
     *
     * @mbg.generated
     */
    private Integer trans;

    /**
     * Database Column Remarks:
     *   转推的格式
     *
     *
     * @mbg.generated
     */
    private String format;

    /**
     *
     * @return the value of tweet2bili.tweet_id
     *
     * @mbg.generated
     */
    public String getTweetId() {
        return tweetId;
    }

    /**
     *
     * @param tweetId the value for tweet2bili.tweet_id
     *
     * @mbg.generated
     */
    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    /**
     *
     * @return the value of tweet2bili.bili_id
     *
     * @mbg.generated
     */
    public Integer getBiliId() {
        return biliId;
    }

    /**
     *
     * @param biliId the value for tweet2bili.bili_id
     *
     * @mbg.generated
     */
    public void setBiliId(Integer biliId) {
        this.biliId = biliId;
    }

    /**
     *
     * @return the value of tweet2bili.follow
     *
     * @mbg.generated
     */
    public Integer getFollow() {
        return follow;
    }

    /**
     *
     * @param follow the value for tweet2bili.follow
     *
     * @mbg.generated
     */
    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    /**
     *
     * @return the value of tweet2bili.trans
     *
     * @mbg.generated
     */
    public Integer getTrans() {
        return trans;
    }

    /**
     *
     * @param trans the value for tweet2bili.trans
     *
     * @mbg.generated
     */
    public void setTrans(Integer trans) {
        this.trans = trans;
    }

    /**
     *
     * @return the value of tweet2bili.format
     *
     * @mbg.generated
     */
    public String getFormat() {
        return format;
    }

    /**
     *
     * @param format the value for tweet2bili.format
     *
     * @mbg.generated
     */
    public void setFormat(String format) {
        this.format = format;
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
        sb.append(", biliId=").append(biliId);
        sb.append(", follow=").append(follow);
        sb.append(", trans=").append(trans);
        sb.append(", format=").append(format);
        sb.append("]");
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("tweetId", tweetId);
        map.put("biliId", biliId);
        map.put("follow", follow);
        map.put("trans", trans);
        map.put("format", format);
        return map;
    }
}