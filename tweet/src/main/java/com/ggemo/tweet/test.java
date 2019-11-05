package com.ggemo.tweet;

import com.alibaba.fastjson.JSON;
import com.ggemo.tweet.pojo.vo.BaiduTransRes;
import com.ggemo.tweet.translate.baidu.TransApi;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        TransApi transApi = new TransApi("20180414000146185", "3nP3pQCeVRZ3sfj6QvXB");
        String text = "【公式四コマ】\n" +
                "『アズールレーン びそくぜんしんっ！』39話・②\n" +
                "「ニーミ、着せられるパターンが多い気がするです」\n" +
                "「断ってもいいのに、結局着てしまうのね＞＜」\n" +
                "「お人好しだから断れない……かも」\n" +
                "次回掲載は11月5日（火）になります！";
        BaiduTransRes res = null;
        try {
            res = transApi.getTransResult(text, "auto", "zh");
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(res.getDst());
    }
}
