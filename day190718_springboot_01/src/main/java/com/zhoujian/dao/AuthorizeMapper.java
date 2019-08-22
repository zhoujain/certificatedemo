package com.zhoujian.dao;

import com.zhoujian.domain.Authorize;
import com.zhoujian.domain.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthorizeMapper {
    //插入
    @Insert("insert into tb_authorize (toolname,model,outnumber,toolId,manufacturer,number,cnumber,coid)" +
            "values(#{toolname},#{model},#{outnumber},#{toolId},#{manufacturer},#{number},#{cnumber},#{company.id})")
    void save(Authorize authorize);
    //查询
    @Select("select * from tb_authorize")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "toolname",property = "toolname"),
            @Result(column = "model",property = "model"),
            @Result(column = "outnumber",property = "outnumber"),
            @Result(column = "toolId",property = "toolId"),
            @Result(column = "manufacturer",property ="manufacturer" ),
            @Result(column = "number",property = "number"),
            @Result(column = "cnumber",property = "cnumber"),
            @Result(column = "coid",property = "company",javaType = Company.class,one =@One(select=
                    "com.zhoujian.dao.CompanyMapper.findById"))

    })
    List<Authorize> findAll();
    //删除
    //修改 多表修改没做
    @Update("update tb_authorize set toolname=#{toolname},model=#{model},outnumber=#{outnumber},toolId=#{toolId},manufacturer=#{manufacturer},number=#{number},cnumber=#{cnumber} where id=#{id}")
    void updateById(Authorize authorize);
}
