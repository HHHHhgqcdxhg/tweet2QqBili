package com.ggemo.tweet.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import twitter4j.Status;

@Getter
@Setter
@AllArgsConstructor
public class StatusWrapper {
    Status status;
    String transed;
}
