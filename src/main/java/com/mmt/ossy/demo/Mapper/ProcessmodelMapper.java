package com.mmt.ossy.demo.Mapper;

import com.mmt.ossy.demo.model.ProcessModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProcessmodelMapper {
    @Select("select * from processModel where state = 0 ORDER BY WORK_TIME ASC LIMIT 501")
    List<ProcessModel> selectReady();
    @Select("select * from processModel where state = 1 ORDER BY WORK_TIME ASC LIMIT 501")
    List<ProcessModel> selectExecute();
    @Select("select * from processModel where state = 9 ORDER BY WORK_TIME ASC LIMIT 501")
    List<ProcessModel> selectChock();
    @Insert("insert into processModel (id, state, work_time) values(#{id},#{state},#{workTime})")
    void add(ProcessModel process);
    @Update("update processModel set state = #{state} where id = #{id}")
    void updateById(@Param("id") int id,@Param("state") int state);
    @Delete("delete from processModel where id = #{id}")
    void DeleteById(int id);
    @Update("update processModel set work_time = #{work_time} where id = #{id}")
    void updateTime(@Param("id") int id,@Param("work_time") int work_time);
}
