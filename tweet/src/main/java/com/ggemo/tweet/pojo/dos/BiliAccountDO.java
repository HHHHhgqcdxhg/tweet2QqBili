package com.ggemo.tweet.pojo.dos;

import java.io.Serializable;

/**
 *
 * @author 清纯的小黄瓜
 */
public class BiliAccountDO implements Serializable {
    /**
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     *
     * @mbg.generated
     */
    private Integer biliId;

    /**
     * Database Column Remarks:
     *   备注名
     *
     *
     * @mbg.generated
     */
    private String backUp;

    /**
     *
     *
     * @mbg.generated
     */
    private String cookie;

    /**
     *
     * @return the value of bili_account.bili_id
     *
     * @mbg.generated
     */
    public Integer getBiliId() {
        return biliId;
    }

    /**
     *
     * @param biliId the value for bili_account.bili_id
     *
     * @mbg.generated
     */
    public void setBiliId(Integer biliId) {
        this.biliId = biliId;
    }

    /**
     *
     * @return the value of bili_account.back_up
     *
     * @mbg.generated
     */
    public String getBackUp() {
        return backUp;
    }

    /**
     *
     * @param backUp the value for bili_account.back_up
     *
     * @mbg.generated
     */
    public void setBackUp(String backUp) {
        this.backUp = backUp;
    }

    /**
     *
     * @return the value of bili_account.cookie
     *
     * @mbg.generated
     */
    public String getCookie() {
        return cookie;
    }

    /**
     *
     * @param cookie the value for bili_account.cookie
     *
     * @mbg.generated
     */
    public void setCookie(String cookie) {
        this.cookie = cookie;
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
        sb.append(", biliId=").append(biliId);
        sb.append(", backUp=").append(backUp);
        sb.append(", cookie=").append(cookie);
        sb.append("]");
        return sb.toString();
    }
}