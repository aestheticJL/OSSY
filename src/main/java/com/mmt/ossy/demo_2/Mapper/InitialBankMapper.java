package com.mmt.ossy.demo_2.Mapper;

import com.mmt.ossy.demo_2.model.InitialBankModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InitialBankMapper {
    @Select("select * from initialBank")
    List<InitialBankModel> getList();
}
