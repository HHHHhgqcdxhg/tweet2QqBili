package com.ggemo.tweet.pojo.vo;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class CmdVO {
    class Follow{
        private String name, nickName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "Follow{" +
                    "name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
    class GroupInfo{
        private String nickName;
        private boolean trans, sendRT, mediaOnly, follow;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public boolean isTrans() {
            return trans;
        }

        public void setTrans(boolean trans) {
            this.trans = trans;
        }

        public boolean isSendRT() {
            return sendRT;
        }

        public void setSendRT(boolean sendRT) {
            this.sendRT = sendRT;
        }

        public boolean isMediaOnly() {
            return mediaOnly;
        }

        public void setMediaOnly(boolean mediaOnly) {
            this.mediaOnly = mediaOnly;
        }

        public boolean isFollow() {
            return follow;
        }

        public void setFollow(boolean follow) {
            this.follow = follow;
        }

        @Override
        public String toString() {
            return "GroupInfo{" +
                    "nickName='" + nickName + '\'' +
                    ", trans=" + trans +
                    ", sendRT=" + sendRT +
                    ", mediaOnly=" + mediaOnly +
                    ", follow=" + follow +
                    '}';
        }
    }
    private Follow follow;
    private GroupInfo groupInfo;

    public void setFollow(Follow follow) {
        this.follow = follow;
    }

    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    @Override
    public String toString() {
        return "CmdVO{" +
                "follow=" + follow +
                ", groupInfo=" + groupInfo +
                '}';
    }

    public static CmdVO getFromCmd(String cmd){
        return getFromJson(cmd.substring(12));
    }

    public static CmdVO getFromJson(String json){
        return JSON.parseObject(json, CmdVO.class);
    }
}
