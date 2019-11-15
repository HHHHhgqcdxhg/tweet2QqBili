package com.ggemo.tweet.common.prehandler;

import com.ggemo.tweet.common.StatusWrapper;
import org.springframework.stereotype.Component;

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

    public static void main(String[] args) {
        String text = "asdfsdfas#碧蓝航线 #爱上杜甫 #qwer 阿士大夫撒大发不出现";
        System.out.println(TagsPreHandler.replaceTag(text));
    }
}
