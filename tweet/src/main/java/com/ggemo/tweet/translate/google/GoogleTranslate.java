package com.ggemo.tweet.translate.google;

// Imports the Google Cloud client library
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.v3beta1.LocationName;
import com.google.cloud.translate.v3beta1.TranslateTextRequest;
import com.google.cloud.translate.v3beta1.TranslateTextResponse;
import com.google.cloud.translate.v3beta1.TranslationServiceClient;

public class GoogleTranslate {
    /**
     * Translates a given text to a target language.
     *
     * @param projectId - Id of the project.
     * @param location - location name.
     * @param text - Text for translation.
     * @param sourceLanguageCode - Language code of text. e.g. "en"
     * @param targetLanguageCode - Language code for translation. e.g. "sr"
     */
    static TranslateTextResponse translateText(
            String projectId,
            String location,
            String text,
            String sourceLanguageCode,
            String targetLanguageCode) {
        try (TranslationServiceClient translationServiceClient = TranslationServiceClient.create()) {

            LocationName locationName =
                    LocationName.newBuilder().setProject(projectId).setLocation(location).build();

            TranslateTextRequest translateTextRequest =
                    TranslateTextRequest.newBuilder()
                            .setParent(locationName.toString())
                            .setMimeType("text/plain")
                            .setSourceLanguageCode(sourceLanguageCode)
                            .setTargetLanguageCode(targetLanguageCode)
                            .addContents(text)
                            .build();

            // Call the API
            TranslateTextResponse response = translationServiceClient.translateText(translateTextRequest);
            System.out.format(
                    "Translated Text: %s", response.getTranslationsList().get(0).getTranslatedText());
            return response;

        } catch (Exception e) {
            throw new RuntimeException("Couldn't create client.", e);
        }
    }
    public static void main(String... args) throws Exception {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "1080");
        translateText("xenon-pier-254010","china","【公式四コマ】\n" +
                "『アズールレーン びそくぜんしんっ！』39話・②\n" +
                "「ニーミ、着せられるパターンが多い気がするです」\n" +
                "「断ってもいいのに、結局着てしまうのね＞＜」\n" +
                "「お人好しだから断れない……かも」\n" +
                "次回掲載は11月5日（火）になります！", "auto","zh-CN");
    }
}
