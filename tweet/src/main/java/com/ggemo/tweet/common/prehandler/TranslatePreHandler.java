package com.ggemo.tweet.common.prehandler;

import com.ggemo.tweet.common.StatusWrapper;
import com.ggemo.tweet.common.util.Image;
import com.ggemo.tweet.common.util.ImageUtil;
import com.ggemo.tweet.translate.Translate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import twitter4j.MediaEntity;
import twitter4j.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TranslatePreHandler implements PreHandler {
    private static final String NAME = "TranslatePreHandler";
    @Autowired
    @Qualifier("BaiduTranslate")
    Translate translate;

    @Override
    public StatusWrapper handle(StatusWrapper statusWrapper) {
        log.info("请求翻译");
        String transed = translate.translate(statusWrapper.getText());
        statusWrapper.setTransed(transed);
        log.info(String.format("transed: %s", transed));
        return statusWrapper;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
