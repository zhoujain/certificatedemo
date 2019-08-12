package com.zhoujian.service.impl;

import com.zhoujian.dao.RecordMapper;
import com.zhoujian.domain.Record;
import com.zhoujian.service.RecordMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordMQServiceImpl implements RecordMQService {

    @Autowired
    private RecordMapper recordMapper;

    public List<Record> queryAllRecord(){
        return recordMapper.getAllRecords();
    }

    @Override
    public List<Record> queryRecordByLogics(String where) {
        return recordMapper.getRecordByLogics(where);
    }

    @Override
    public void delRecordByCid(Integer cid) {
        recordMapper.delRecord(cid);
    }
}
