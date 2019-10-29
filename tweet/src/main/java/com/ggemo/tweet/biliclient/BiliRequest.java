package com.ggemo.tweet.biliclient;

import org.apache.http.HttpEntity;

import java.util.Collection;

/**
 * @author Black Lotus
 */
public interface BiliRequest {
    /**
     * 发送动态的方法(无图)
     * @param text
     * @return
     */
    HttpEntity create(String text);

    /**
     * 发送动态的方法(有图)
     * @param text
     * @param imgUrls
     * @return
     */
    HttpEntity create(String text, Collection<String> imgUrls);
}
