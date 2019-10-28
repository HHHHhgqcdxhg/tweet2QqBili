package com.ggemo.tweet.pojo.dos;

import java.io.Serializable;

/**
 *
 * @author 清纯的小黄瓜
 */
public class QqGroupInfoDO implements Serializable {
    /**
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * Database Column Remarks:
     *   裙号
     *
     *
     * @mbg.generated
     */
    private Integer groupId;

    /**
     * Database Column Remarks:
     *   群名,让bot管理员能认出是哪个群的名字
     *
     *
     * @mbg.generated
     */
    private String groupNickName;

    /**
     *
     * @return the value of qq_group_info.group_id
     *
     * @mbg.generated
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     *
     * @param groupId the value for qq_group_info.group_id
     *
     * @mbg.generated
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     *
     * @return the value of qq_group_info.group_nick_name
     *
     * @mbg.generated
     */
    public String getGroupNickName() {
        return groupNickName;
    }

    /**
     *
     * @param groupNickName the value for qq_group_info.group_nick_name
     *
     * @mbg.generated
     */
    public void setGroupNickName(String groupNickName) {
        this.groupNickName = groupNickName;
    }

    /**
     * @return
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", groupId=").append(groupId);
        sb.append(", groupNickName=").append(groupNickName);
        sb.append("]");
        return sb.toString();
    }
}