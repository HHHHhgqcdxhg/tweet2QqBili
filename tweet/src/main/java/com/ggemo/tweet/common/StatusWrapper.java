package com.ggemo.tweet.common;

import com.ggemo.tweet.common.util.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import twitter4j.Status;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StatusWrapper {
    Status status;
    String transed;
    List<Image> images;
    String removedUrlText;

    public StatusWrapper(Status status) {
        this.status = status;
    }
}
