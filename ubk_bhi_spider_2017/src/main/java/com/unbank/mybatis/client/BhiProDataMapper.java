package com.unbank.mybatis.client;

import com.unbank.mybatis.vo.BhiProData;
import com.unbank.mybatis.vo.BhiProDataExample;
import com.unbank.mybatis.vo.BhiProDataWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BhiProDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int countByExample(BhiProDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int deleteByExample(BhiProDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int insert(BhiProDataWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int insertSelective(BhiProDataWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    List<BhiProDataWithBLOBs> selectByExampleWithBLOBs(BhiProDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    List<BhiProData> selectByExample(BhiProDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    BhiProDataWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int updateByExampleSelective(@Param("record") BhiProDataWithBLOBs record, @Param("example") BhiProDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int updateByExampleWithBLOBs(@Param("record") BhiProDataWithBLOBs record, @Param("example") BhiProDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int updateByExample(@Param("record") BhiProData record, @Param("example") BhiProDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int updateByPrimaryKeySelective(BhiProDataWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int updateByPrimaryKeyWithBLOBs(BhiProDataWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bhi_pro_data
     *
     * @mbggenerated Thu Jul 07 09:45:46 GMT+08:00 2016
     */
    int updateByPrimaryKey(BhiProData record);
}