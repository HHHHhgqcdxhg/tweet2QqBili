package com.ggemo.tweet;

import com.ggemo.tweet.tweet.StatusListener;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class StartTweet {
    public static void main(String[] args) {
        TwitterStream stream = new TwitterStreamFactory().getInstance();
        stream.addListener(new StatusListener());
        FilterQuery filterQuery = new FilterQuery(new String[]{});
        stream.filter();
    }

}
