package com.ggemo.tweet.pojo.vo;
class Follow {
    String name;
    String nickName;

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
