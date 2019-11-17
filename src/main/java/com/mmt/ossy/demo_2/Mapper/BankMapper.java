package com.mmt.ossy.demo_2.Mapper;

import com.mmt.ossy.demo_2.model.BankModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BankMapper {
    @Select("select * from Bank where processId = 0")
    BankModel getBankList();
    @Select("select * from Bank where processId = #{processId}")
    BankModel getProcessList(Integer processId);
    @Select("select * from Bank where processId in (1,2,3,4,5)")
    List<BankModel> getAllProcessList();
    @Update("update bank set availableA =availableA + #{addA},availableB =availableB + #{addB},availableC =availableC + #{addC} where processId = #{processId}")
    void gainById(@Param("processId") Integer processId,@Param("addA") Integer addA,@Param("addB") Integer addB,@Param("addC") Integer addC);
    @Update("update bank set availableA = availableA-#{addA},availableB = availableB-#{addB},availableC = availableC-#{addC} where processId = 0")
    void gainBank(@Param("addA") Integer addA,@Param("addB") Integer addB,@Param("addC") Integer addC);
    @Update("update bank set availableA = 0,availableB = 0,availableC = 0 where processId in (1,2,3,4,5)")
    void initialProcess();
    @Update("update bank set availableA = 10,availableB = 5,availableC = 7 where processId = 0")
    void initialBank();
    @Update("update bank set availableA = 0,availableB = 0,availableC = 0 where processId = #{processId}")
    void removeById(Integer processId);
    @Update("update bank set availableA = availableA+#{availableA},availableB = availableB+#{availableB},availableC = availableC+#{availableC} where processId = 0")
    void removeToBank(@Param("availableA")Integer availableA, @Param("availableB")Integer availableB, @Param("availableC")Integer availableC);
}
