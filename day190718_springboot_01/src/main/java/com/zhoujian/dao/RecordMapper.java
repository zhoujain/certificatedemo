package com.zhoujian.dao;

import com.zhoujian.domain.Certificate;
import com.zhoujian.domain.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("recordMapper")
@Mapper
public interface RecordMapper {
    /**
     * 查询全部
     * @return
     */
    List<Record> getAllRecords();

    /**
     * 按id查询
     * @param id
     * @return
     */
    Record getRecordById(Integer id);

    /**
     * 添加一个证书并返回受影响的行数
     * @param
     * @return
     */
    int addRecord(Record record);

    /**
     * 按id删除
     * @param
     * @return
     */
    Integer delRecord(Integer cid);

    /**
     * 更新数据库
     * @param
     * @return
     */
    Integer updateRecord(Record record);

    /**
     * 根据where条件查询
     * @param where
     * @return
     */
    List<Record> getRecordByLogics(String where);

    String queryMaxScnumber();



    Record queryScnumber(String cnumber);

    Integer updateCid(Map map);
}
