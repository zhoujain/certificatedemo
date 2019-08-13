package com.zhoujian.service.impl;

import com.zhoujian.dao.RecordMapper;
import com.zhoujian.domain.Record;
import com.zhoujian.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("recordService")
public class RecordServiceImpl implements RecordService {
    @Resource(name = "recordMapper")
    RecordMapper recordMapper;

    @Override
    public Record queryScnumber(String scnumber){
        Record result = recordMapper.queryScnumber(scnumber);
        return result;
    }

    @Override
    public Integer addRecord(Record record){
        int result = recordMapper.addRecord(record);
        return record.getScid();
    }

    @Override
    public Integer updateRecord(Record record){
        Integer result = recordMapper.updateRecord(record);
        return result;
    }

    @Override
    public Integer queryMaxScnumber(){
        Integer maxScnumber = Integer.parseInt(recordMapper.queryMaxScnumber());
        return  maxScnumber;
    }

    @Override
    public Record getRecordById(Integer scid){
        return recordMapper.getRecordById(scid);
    }

    @Override
    public Integer updateCid(Map map){
        Integer result=recordMapper.updateCid(map);
        return  result;
    }
}
