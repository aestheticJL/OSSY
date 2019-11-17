package com.mmt.ossy.demo_2.Mapper;

import com.mmt.ossy.demo_2.model.BankModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BankMapper {
    @Select("select * from Bank where processId = 0")
    BankModel getBankList();
    @Select("select * from Bank where processId = #{processId}")
    BankModel getProcessList(Integer processId);
    @Update("update bank set availableA = #{addA},availableB = #{addB},availableC = #{addC} where processId = #{processId}")
    void gainById(@Param("processId") Integer processId,@Param("addA") Integer addA,@Param("addB") Integer addB,@Param("addC") Integer addC);
    @Update("update bank set availableA = availableA-#{addA},availableB = availableB-#{addB},availableC = availableC-#{addC} where processId = 0")
    void gainBank(@Param("addA") Integer addA,@Param("addB") Integer addB,@Param("addC") Integer addC);
}
