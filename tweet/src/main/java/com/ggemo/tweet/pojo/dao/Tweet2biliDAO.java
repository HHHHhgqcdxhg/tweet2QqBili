package com.ggemo.tweet.pojo.dao;

import com.ggemo.tweet.pojo.dos.Tweet2biliDO;
import com.ggemo.tweet.pojo.dos.Tweet2biliParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * This class was generated by Ali-Generator
 * @author 清纯的小黄瓜
 */
@Mapper
public interface Tweet2biliDAO {
    /**
     * @param tweet2biliParam
     * @return
     *
     * @mbg.generated
     */
    long countByParam(Tweet2biliParam tweet2biliParam);

    /**
     * @param tweet2biliParam
     * @return
     *
     * @mbg.generated
     */
    int deleteByParam(Tweet2biliParam tweet2biliParam);

    /**
     * @param tweetId
     * @param biliId
     * @return
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(@Param("tweetId") String tweetId, @Param("biliId") Integer biliId);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int insert(Tweet2biliDO record);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int insertSelective(Tweet2biliDO record);

    /**
     * @param tweet2biliParam
     * @return
     *
     * @mbg.generated
     */
    List<Tweet2biliDO> selectByParam(Tweet2biliParam tweet2biliParam);

    /**
     * @param tweetId
     * @param biliId
     * @return
     *
     * @mbg.generated
     */
    Tweet2biliDO selectByPrimaryKey(@Param("tweetId") String tweetId, @Param("biliId") Integer biliId);

    /**
     * @param record
     * @param tweet2biliParam
     * @return
     *
     * @mbg.generated
     */
    int updateByParamSelective(@Param("record") Tweet2biliDO record, @Param("tweet2biliParam") Tweet2biliParam tweet2biliParam);

    /**
     * @param record
     * @param tweet2biliParam
     * @return
     *
     * @mbg.generated
     */
    int updateByParam(@Param("record") Tweet2biliDO record, @Param("tweet2biliParam") Tweet2biliParam tweet2biliParam);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Tweet2biliDO record);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Tweet2biliDO record);

    /**
     * @param records
     * @return
     *
     * @mbg.generated
     */
    int batchInsert(List<Tweet2biliDO> records);
}