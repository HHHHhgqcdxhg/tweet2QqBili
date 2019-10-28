package com.ggemo.tweet;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String cookieStr = "[\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1601350003,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"_uuid\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"0D9B61C4-94BD-A111-9420-7971CDA2B2BB03656infoc\",\n" +
                "    \"id\": 1\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1572406018.911886,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"bili_jct\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"ed7f739535a7c0947dc4e1a9aeb0ca49\",\n" +
                "    \"id\": 2\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1572417947,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"bp_t_offset_300123440\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"304886658763045518\",\n" +
                "    \"id\": 3\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1664422003.73397,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"buvid3\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"DB8EC569-E41D-44DD-A65C-350AA5BF0264155835infoc\",\n" +
                "    \"id\": 4\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1572406018.911792,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"DedeUserID\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"300123440\",\n" +
                "    \"id\": 5\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1572406018.91184,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"DedeUserID__ckMd5\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"c8e1e3596cff4df9\",\n" +
                "    \"id\": 6\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 253402300799,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"dy_spec_agreed\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"1\",\n" +
                "    \"id\": 7\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1572417739,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"finger\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"3f3919d0\",\n" +
                "    \"id\": 8\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1601351133,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"im_notify_type_300123440\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"0\",\n" +
                "    \"id\": 9\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 2177452800.070593,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"LIVE_BUVID\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"AUTO5915698140028511\",\n" +
                "    \"id\": 10\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1572406018.911865,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": true,\n" +
                "    \"name\": \"SESSDATA\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"2a07c4a5%2C1572406018%2Cb6040791\",\n" +
                "    \"id\": 11\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1601350005.44149,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"sid\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"7t8ee663\",\n" +
                "    \"id\": 12\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".bilibili.com\",\n" +
                "    \"expirationDate\": 1585539157,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"UM_distinctid\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"16d803a2720303-096eefaf8974fe-67e1b3f-7e9000-16d803a27212da\",\n" +
                "    \"id\": 13\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".t.bilibili.com\",\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"Hm_lpvt_8a6e55dbd2870f0f5bc9194cddf32a02\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": true,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"1569825947\",\n" +
                "    \"id\": 14\n" +
                "},\n" +
                "{\n" +
                "    \"domain\": \".t.bilibili.com\",\n" +
                "    \"expirationDate\": 1601361947,\n" +
                "    \"hostOnly\": false,\n" +
                "    \"httpOnly\": false,\n" +
                "    \"name\": \"Hm_lvt_8a6e55dbd2870f0f5bc9194cddf32a02\",\n" +
                "    \"path\": \"/\",\n" +
                "    \"sameSite\": \"unspecified\",\n" +
                "    \"secure\": false,\n" +
                "    \"session\": false,\n" +
                "    \"storeId\": \"1\",\n" +
                "    \"value\": \"1569825669,1569825891,1569825903,1569825947\",\n" +
                "    \"id\": 15\n" +
                "}\n" +
                "]";

        List<Map<String, Object>> cookieList = (List<Map<String, Object>>) JSON.parse(cookieStr);
        Map<String, Object> realCookies = new HashMap<>();
        for (Map<String, Object> map : cookieList) {
            realCookies.put((String) map.get("name"), map.get("value"));
        }
    }
}
