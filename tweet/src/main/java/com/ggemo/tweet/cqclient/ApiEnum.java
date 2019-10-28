package com.ggemo.tweet.cqclient;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.http.HttpRequest;

@Getter
public enum ApiEnum {
    /**
     * 发送私聊消息
     */
    SEND_PRIVATE_MSG("send_private_msg"),

    /**
     * 发送群消息
     */
    SEND_GROUP_MSG("send_group_msg"),

    /**
     * 发送消息
     */
    SEND_MSG("send_msg");


    private String url;

    ApiEnum(String url) {
        this.url = url;
    }
}