package com.ggemo.tweet.translate;

import org.springframework.stereotype.Component;

@Component
public interface Translate {
    public String translate(String text);
}
