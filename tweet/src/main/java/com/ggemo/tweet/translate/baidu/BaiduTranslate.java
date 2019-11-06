package com.ggemo.tweet.translate.baidu;

import com.ggemo.tweet.pojo.vo.BaiduTransRes;
import com.ggemo.tweet.translate.Translate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service("BaiduTranslate")
public class BaiduTranslate implements Translate {
    @Value("${baiduTranslate.appid}")
    String appId;

    @Value("${baiduTranslate.securityKey}")
    String securityKey;

    private TransApi transApi;

    private static final String FROM = "auto", TO = "zh";

    @PostConstruct
    public void init(){
        transApi = new TransApi(appId, securityKey);
    }

    @Override
    public String translate(String text){
        BaiduTransRes res = null;
        try {
            res = transApi.getTransResult(text, FROM, TO);
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
            e.printStackTrace();
            log.error("", e);
        }
        if (res != null) {
            return res.getDst();
        }else{
            return "";
        }
    }
}
