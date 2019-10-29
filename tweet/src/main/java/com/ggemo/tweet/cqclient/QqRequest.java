package com.ggemo.tweet.cqclient;

import org.apache.http.HttpEntity;

/**
 * @author 清纯的小黄瓜
 */
public interface QqRequest {

    /**
     * 往qq群发消息的接口
     * @param groupId
     * @param msg
     * @return
     */
    HttpEntity sendGroupMsg(int groupId, String msg);
}
