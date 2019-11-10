package com.ggemo.tweet.pojo.dao;

import com.ggemo.tweet.pojo.dos.QqGroupInfoDO;
import com.ggemo.tweet.pojo.dos.QqGroupInfoParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * This class was generated by Ali-Generator
 * @author 清纯的小黄瓜
 */
@Mapper
public interface QqGroupInfoDAO {
    /**
     * @param qqGroupInfoParam
     * @return
     *
     * @mbg.generated
     */
    long countByParam(QqGroupInfoParam qqGroupInfoParam);

    /**
     * @param qqGroupInfoParam
     * @return
     *
     * @mbg.generated
     */
    int deleteByParam(QqGroupInfoParam qqGroupInfoParam);

    /**
     * @param groupId
     * @return
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long groupId);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int insert(QqGroupInfoDO record);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int insertSelective(QqGroupInfoDO record);

    /**
     * @param qqGroupInfoParam
     * @return
     *
     * @mbg.generated
     */
    List<QqGroupInfoDO> selectByParam(QqGroupInfoParam qqGroupInfoParam);

    /**
     * @param groupId
     * @return
     *
     * @mbg.generated
     */
    QqGroupInfoDO selectByPrimaryKey(Long groupId);

    /**
     * @param record
     * @param qqGroupInfoParam
     * @return
     *
     * @mbg.generated
     */
    int updateByParamSelective(@Param("record") QqGroupInfoDO record, @Param("qqGroupInfoParam") QqGroupInfoParam qqGroupInfoParam);

    /**
     * @param record
     * @param qqGroupInfoParam
     * @return
     *
     * @mbg.generated
     */
    int updateByParam(@Param("record") QqGroupInfoDO record, @Param("qqGroupInfoParam") QqGroupInfoParam qqGroupInfoParam);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(QqGroupInfoDO record);

    /**
     * @param record
     * @return
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(QqGroupInfoDO record);

    /**
     * @param records
     * @return
     *
     * @mbg.generated
     */
    int batchInsert(List<QqGroupInfoDO> records);
}