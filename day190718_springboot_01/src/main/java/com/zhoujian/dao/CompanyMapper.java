package com.zhoujian.dao;

import com.zhoujian.domain.Company;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CompanyMapper {


    //插入
    @Insert("insert into tb_company (name,linkMan,linkPhone,adate,pdate,adress,aid)" +
            "values(#{name},#{linkMan},#{linkPhone},#{adate},#{pdate},#{adress},#{aid}")
    void save(Company company);

    @Select("select * from tb_company")
    List<Company>findAll();

    @Select("select * from tb_company where id =#{id}")
    Company findById(Integer id);

    @Update("update tb_company set name=#{name},linkMan=#{linkMan},linkPhone=#{linkPhone},adate=#{adate},pdate=#{pdate},adress=#{adress} where id=#{id}")
    void updateById(Company company);
}
