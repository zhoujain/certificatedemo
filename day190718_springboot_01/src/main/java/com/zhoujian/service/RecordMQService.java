package com.zhoujian.service;

import com.zhoujian.domain.Record;

import java.util.List;

public interface RecordMQService {
    List<Record> queryAllRecord();

    List<Record> queryRecordByLogics(String where);

    void delRecordByCid(Integer cid);
}
