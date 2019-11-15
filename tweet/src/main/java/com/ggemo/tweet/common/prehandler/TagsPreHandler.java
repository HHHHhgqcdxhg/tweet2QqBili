package com.ggemo.tweet.common.prehandler;

import com.ggemo.tweet.common.StatusWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TagsPreHandler implements PreHandler {
    private static final String NAME = "TagsPreHandler";

    @Override
    public StatusWrapper handle(StatusWrapper statusWrapper) {
        String text = statusWrapper.getText();
        statusWrapper.setText(replaceTag(text));
        return statusWrapper;
    }

    private static String replaceTag(String text){
        text = text.replaceAll("#[^ ]* ", "");
        return text;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
