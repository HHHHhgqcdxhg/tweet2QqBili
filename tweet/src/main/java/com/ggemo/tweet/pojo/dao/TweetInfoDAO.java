package com.ggemo.tweet.pojo.dao;

import com.ggemo.tweet.pojo.dos.TweetInfoDO;
import com.ggemo.tweet.pojo.dos.TweetInfoParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * This class was generated by Ali-Generator
 * @author 清纯的小黄瓜
 */
@Mapper
public interface TweetInfoDAO {
    /**
     * @param tweetInfoParam
     * @return
     *
     * @mbg.generated
     */
    long countByParam(TweetInfoParam tweetInfoParam);

    /**
     * @param tweetInfoParam
     * @return
     *
     * @mbg.generated
     */
    int deleteByParam(TweetInfoParam tweetInfoParam);

    /**
     * @param tweetId
     * @return
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String tweetId);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int insert(TweetInfoDO record);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int insertSelective(TweetInfoDO record);

    /**
     * @param tweetInfoParam
     * @return
     *
     * @mbg.generated
     */
    List<TweetInfoDO> selectByParam(TweetInfoParam tweetInfoParam);

    /**
     * @param tweetId
     * @return
     *
     * @mbg.generated
     */
    TweetInfoDO selectByPrimaryKey(String tweetId);

    /**
     * @param record
     * @param tweetInfoParam
     * @return
     *
     * @mbg.generated
     */
    int updateByParamSelective(@Param("record") TweetInfoDO record, @Param("tweetInfoParam") TweetInfoParam tweetInfoParam);

    /**
     * @param record
     * @param tweetInfoParam
     * @return
     *
     * @mbg.generated
     */
    int updateByParam(@Param("record") TweetInfoDO record, @Param("tweetInfoParam") TweetInfoParam tweetInfoParam);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TweetInfoDO record);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TweetInfoDO record);

    /**
     * @param records
     * @return
     *
     * @mbg.generated
     */
    int batchInsert(List<TweetInfoDO> records);
}