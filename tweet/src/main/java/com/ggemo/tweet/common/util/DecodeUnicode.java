package com.ggemo.tweet.common.util;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

public class DecodeUnicode {
    public static String decode(String string) {
        byte[] utf8 = string.getBytes(StandardCharsets.UTF_8);
        // Convert from UTF-8 to Unicode
        string = new String(utf8, StandardCharsets.UTF_8);
        return string;
    }
}
