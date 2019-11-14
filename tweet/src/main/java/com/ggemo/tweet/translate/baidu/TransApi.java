package com.ggemo.tweet.translate.baidu;

import com.alibaba.fastjson.JSON;
import com.ggemo.tweet.common.util.DecodeUnicode;
import com.ggemo.tweet.pojo.vo.BaiduTransRes;
import com.ggemo.tweet.translate.baidu.MD5;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public BaiduTransRes getTransResult(String query, String from, String to) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        Map<String, String> params = buildParams(query, from, to);
        String resUnicode = HttpGet.get(TRANS_API_HOST, params);
        return JSON.parseObject(DecodeUnicode.decode(resUnicode), BaiduTransRes.class);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", com.ggemo.tweet.translate.baidu.MD5.md5(src));

        return params;
    }

}
