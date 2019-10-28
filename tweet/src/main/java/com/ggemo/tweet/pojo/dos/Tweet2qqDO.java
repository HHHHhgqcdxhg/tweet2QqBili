package com.ggemo.tweet.pojo.dos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 清纯的小黄瓜
 */
public class Tweet2qqDO implements Serializable {
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
     *
     *
     * @mbg.generated
     */
    private Integer qqGroupId;

    /**
     * Database Column Remarks:
     *   boolean, 是否关注
     *
     *
     * @mbg.generated
     */
    private Integer follow;

    /**
     * Database Column Remarks:
     *   群里给定的推主的昵称
     *
     *
     * @mbg.generated
     */
    private String tweetNickName;

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
     *   boolean, 是否转发rt的推文
     *
     *
     * @mbg.generated
     */
    private Integer sendRt;

    /**
     * Database Column Remarks:
     *   boolean, 是否只发送有图片的推文
     *
     *
     * @mbg.generated
     */
    private Integer mediaOnly;

    /**
     * Database Column Remarks:
     *   排序
     *
     *
     * @mbg.generated
     */
    private Integer order;

    /**
     * Database Column Remarks:
     *   转推消息格式
     *
     *
     * @mbg.generated
     */
    private String format;

    /**
     *
     * @return the value of tweet2qq.tweet_id
     *
     * @mbg.generated
     */
    public String getTweetId() {
        return tweetId;
    }

    /**
     *
     * @param tweetId the value for tweet2qq.tweet_id
     *
     * @mbg.generated
     */
    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    /**
     *
     * @return the value of tweet2qq.qq_group_id
     *
     * @mbg.generated
     */
    public Integer getQqGroupId() {
        return qqGroupId;
    }

    /**
     *
     * @param qqGroupId the value for tweet2qq.qq_group_id
     *
     * @mbg.generated
     */
    public void setQqGroupId(Integer qqGroupId) {
        this.qqGroupId = qqGroupId;
    }

    /**
     *
     * @return the value of tweet2qq.follow
     *
     * @mbg.generated
     */
    public Integer getFollow() {
        return follow;
    }

    /**
     *
     * @param follow the value for tweet2qq.follow
     *
     * @mbg.generated
     */
    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    /**
     *
     * @return the value of tweet2qq.tweet_nick_name
     *
     * @mbg.generated
     */
    public String getTweetNickName() {
        return tweetNickName;
    }

    /**
     *
     * @param tweetNickName the value for tweet2qq.tweet_nick_name
     *
     * @mbg.generated
     */
    public void setTweetNickName(String tweetNickName) {
        this.tweetNickName = tweetNickName;
    }

    /**
     *
     * @return the value of tweet2qq.trans
     *
     * @mbg.generated
     */
    public Integer getTrans() {
        return trans;
    }

    /**
     *
     * @param trans the value for tweet2qq.trans
     *
     * @mbg.generated
     */
    public void setTrans(Integer trans) {
        this.trans = trans;
    }

    /**
     *
     * @return the value of tweet2qq.send_rt
     *
     * @mbg.generated
     */
    public Integer getSendRt() {
        return sendRt;
    }

    /**
     *
     * @param sendRt the value for tweet2qq.send_rt
     *
     * @mbg.generated
     */
    public void setSendRt(Integer sendRt) {
        this.sendRt = sendRt;
    }

    /**
     *
     * @return the value of tweet2qq.media_only
     *
     * @mbg.generated
     */
    public Integer getMediaOnly() {
        return mediaOnly;
    }

    /**
     *
     * @param mediaOnly the value for tweet2qq.media_only
     *
     * @mbg.generated
     */
    public void setMediaOnly(Integer mediaOnly) {
        this.mediaOnly = mediaOnly;
    }

    /**
     *
     * @return the value of tweet2qq.order_
     *
     * @mbg.generated
     */
    public Integer getOrder() {
        return order;
    }

    /**
     *
     * @param order the value for tweet2qq.order_
     *
     * @mbg.generated
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     *
     * @return the value of tweet2qq.format
     *
     * @mbg.generated
     */
    public String getFormat() {
        return format;
    }

    /**
     *
     * @param format the value for tweet2qq.format
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
        sb.append(", qqGroupId=").append(qqGroupId);
        sb.append(", follow=").append(follow);
        sb.append(", tweetNickName=").append(tweetNickName);
        sb.append(", trans=").append(trans);
        sb.append(", sendRt=").append(sendRt);
        sb.append(", mediaOnly=").append(mediaOnly);
        sb.append(", order=").append(order);
        sb.append(", format=").append(format);
        sb.append("]");
        return sb.toString();
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        map.put("tweetId", tweetId);
        map.put("qqGroupId", qqGroupId);
        map.put("follow", follow);
        map.put("tweetNickName", tweetNickName);
        map.put("trans", trans);
        map.put("sendRt", sendRt);
        map.put("mediaOnly", mediaOnly);
        map.put("order_", order);
        map.put("format", format);
        return map;
    }
}