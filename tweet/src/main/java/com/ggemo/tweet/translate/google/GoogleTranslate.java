package com.ggemo.tweet.translate.google;

// Imports the Google Cloud client library


import com.ggemo.tweet.translate.Translate;
import com.google.cloud.translate.v3beta1.LocationName;
import com.google.cloud.translate.v3beta1.TranslateTextRequest;
import com.google.cloud.translate.v3beta1.TranslateTextResponse;
import com.google.cloud.translate.v3beta1.TranslationServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service("GoogleTranslate")
public class GoogleTranslate implements Translate {
    @Value("${googleTranslate.projectId}")
    String projectId;

    private static final String LOCATION = "global", SOURCE_LANGUAGE_CODE = "auto", RATGET_LANGUAGE_CODE = "zh-CN";

    private TranslateTextRequest.Builder builder;

    private ThreadLocal<TranslateTextRequest.Builder> builderThreadLocal;
    private TranslationServiceClient translationServiceClient;

    @PostConstruct
    public void init() {
        LocationName locationName =
                LocationName.newBuilder().setProject(projectId).setLocation(LOCATION).build();
        try {
            translationServiceClient = TranslationServiceClient.create();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't create google translate client.", e);
        }
        this.builder =
                TranslateTextRequest.newBuilder()
                        .setParent(locationName.toString())
                        .setMimeType("text/plain")
                        .setSourceLanguageCode(SOURCE_LANGUAGE_CODE)
                        .setTargetLanguageCode(RATGET_LANGUAGE_CODE);
        builderThreadLocal = new ThreadLocal<>();

    }

    @Override
    public String translate(String text) {
        builderThreadLocal.set(this.builder);
        TranslateTextRequest.Builder builder = builderThreadLocal.get();
        try {
            TranslateTextRequest translateTextRequest = builder.addContents(text).build();
            TranslateTextResponse response = translationServiceClient.translateText(translateTextRequest);
            return response.getTranslationsList().get(0).getTranslatedText();
        } finally {
            builderThreadLocal.remove();
        }
    }

    public static void main(String... args) throws Exception {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "1080");
//        translate("xenon-pier-254010", "china", "【公式四コマ】\n" +
//                "『アズールレーン びそくぜんしんっ！』39話・②\n" +
//                "「ニーミ、着せられるパターンが多い気がするです」\n" +
//                "「断ってもいいのに、結局着てしまうのね＞＜」\n" +
//                "「お人好しだから断れない……かも」\n" +
//                "次回掲載は11月5日（火）になります！", "ja", "zh-CN");
    }
}
