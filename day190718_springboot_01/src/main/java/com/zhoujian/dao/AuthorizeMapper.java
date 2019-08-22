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
    //查询所有
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
    //根据id进行查询
    @Select("select * from tb_authorize where id = #{id}")
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
    Authorize findById(Integer id);
    //删除
    //修改 多表修改没做
    @Update("update tb_authorize set toolname=#{toolname},model=#{model},outnumber=#{outnumber},toolId=#{toolId},manufacturer=#{manufacturer},number=#{number},cnumber=#{cnumber} where id=#{id}")
    void updateById(Authorize authorize);
    //根据登记号模糊查询
    @Select("select * from tb_authorize where cnumber like '%${value}%'")
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
    List<Authorize> findAllByLikeCnumber(String cnumber);

    //根据委托号模糊查询
    @Select("select * from tb_authorize where coid in (select id from tb_company where aid like '%${value}%')")
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
    List<Authorize> findAllByLikeaid(String aid);
}
