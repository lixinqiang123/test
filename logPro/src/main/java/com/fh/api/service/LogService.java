package com.fh.api.service;

import com.fh.api.entity.po.MongoLog;
import com.fh.api.entity.vo.Params;

import java.util.List;
import java.util.Map;

public interface LogService {
    List<MongoLog> queryLog();

    Map queryLogPage(Params params);

    void updateLog(MongoLog mongoLog);

    void addMongoLog(MongoLog mongoLog);

    List<MongoLog> queryLogAll();
}
