package com.ggemo.tweet.biliclient;

import org.apache.http.HttpEntity;

import java.util.Collection;

/**
 * @author: Black Lotus
 */
public class BiliRequestImpl implements BiliRequest {
    @Override
    public HttpEntity create(String text) {
        return null;
    }

    @Override
    public HttpEntity create(String text, Collection<String> imgUrls) {
        return null;
    }
}
