package com.ggemo.tweet.common;

import com.ggemo.tweet.common.util.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import twitter4j.Status;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StatusWrapper {
    Status status;
    String transed;
    List<Image> images;
    String text;
    String biliDynamicUrl;

    public StatusWrapper(Status status) {
        this.status = status;
        this.text = status.getText();
    }
}
