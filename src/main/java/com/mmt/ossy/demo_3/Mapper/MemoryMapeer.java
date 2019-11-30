package com.mmt.ossy.demo_3.Mapper;

import com.mmt.ossy.demo_3.model.MemoryModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemoryMapeer {
    @Select("select * from memory ORDER BY begin ASC LIMIT 501")
    List<MemoryModel> getList();

    @Delete("delete from memory where id = #{id}")
    void remove(Integer id);

    @Insert("insert into memory (Id, begin, end) values(#{Id},#{begin},#{end})")
    void add(@Param("Id") Integer Id, @Param("begin") Integer begin, @Param("end") Integer end);
}
