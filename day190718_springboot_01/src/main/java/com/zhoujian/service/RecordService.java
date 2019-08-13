package com.zhoujian.service;

import com.zhoujian.domain.Record;

import java.util.Map;

public interface RecordService {
    Record queryScnumber(String scnumber);

    Integer addRecord(Record record);

    Integer updateRecord(Record record);

    Integer queryMaxScnumber();

    Record getRecordById(Integer scid);

    Integer updateCid(Map map);
}
