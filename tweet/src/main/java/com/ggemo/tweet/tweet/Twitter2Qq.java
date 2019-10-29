package com.ggemo.tweet.tweet;

import com.ggemo.tweet.pojo.dos.QqGroupInfoDO;
import com.ggemo.tweet.pojo.dos.Tweet2qqDO;
import twitter4j.Status;

public class Twitter2Qq {
    public static void post(Status status, Tweet2qqDO qqGroup){
        String[] messages = qqGroup.getFormat().split("\\|");
    }
}
