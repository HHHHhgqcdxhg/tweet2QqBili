package com.ggemo.tweet.common.prehandler;

import com.ggemo.tweet.common.StatusWrapper;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TextRemoveImgUrlPreHandler implements PreHandler {
    private static final Pattern URL_PATTERN = Pattern.compile("https?://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
    private static final String NAME = "TextRemoveImgUrlPreHandler";
    @Override
    public StatusWrapper handle(StatusWrapper statusWrapper) {
        String text = statusWrapper.getText();
        statusWrapper.setText(removeUrl(text));
        return statusWrapper;
    }

    private static String removeUrl(String text) {
        Matcher matcher = URL_PATTERN.matcher(text);
        List<String> splited = new ArrayList<>();
        List<Boolean> along = new ArrayList<>();
        int start, end = 0;
        while (matcher.find()) {
            start = matcher.start();
            String newString = text.substring(end, start);
            if (!newString.isEmpty()) {
                splited.add(newString);
                if(newString.isBlank()){
                    along.add(true);
                }else {
                    along.add(false);
                }
            }
            end = matcher.end();
            splited.add(text.substring(start, end));
            along.add(true);
        }
        if(splited.size() == 0){
            return text;
        }
        int i;
        boolean add = false;
        StringBuilder sb = new StringBuilder();
        for (i = splited.size() - 1; i >= 0; i--) {
            if (!add) {
                if (!along.get(i)) {
                    add = true;
                    sb.insert(0, splited.get(i));
                }
            } else {
                sb.insert(0, splited.get(i));
            }
        }
        return sb.toString();
    }

    @Override
    public String getName() {
        return NAME;
    }
}
