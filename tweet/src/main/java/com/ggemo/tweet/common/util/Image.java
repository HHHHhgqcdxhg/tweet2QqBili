package com.ggemo.tweet.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Image {
    private String rawUrl;
    private String localPath;
    private String publicUrl;
}
