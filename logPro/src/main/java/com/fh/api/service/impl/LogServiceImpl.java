package com.fh.api.service.impl;

import com.fh.api.entity.po.MongoLog;
import com.fh.api.entity.vo.Params;
import com.fh.api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<MongoLog> queryLog() {
        return mongoTemplate.findAll(MongoLog.class);
    }


    @Override
    public Map queryLogPage(Params params) {

        Map map = new HashMap();

        //创建查询对象
        Query query=new Query();

        //查询总条数
        long count = mongoTemplate.count(query, MongoLog.class);

        //构建查询分页信息
        query.skip((params.getPage()-1)*params.getSize());
        
        query.limit(params.getSize());
        
        //根据条件查询数据
        List<MongoLog> mongoLogList = mongoTemplate.find(query, MongoLog.class);

        map.put("count",count);
        map.put("data",mongoLogList);

        return map;
    }

    /**
     * 修改
     *
     * @param mongoLog
     */
    @Override
    public void updateLog(MongoLog mongoLog) {

        //先构建查询对象
        Query query=new Query(Criteria.where("_id").is(mongoLog.getId()));

        //创建update对象
        Update update=new Update().set("name",mongoLog.getName()).set("logDate",mongoLog.getLogDate()).
                set("author",mongoLog.getAuthor()).set("content",mongoLog.getContent());

        //进行修改
        //upsert 更新对象不存在 新增一条
        mongoTemplate.upsert(query,update,MongoLog.class);
    }

    /**
     *
     * 新增
     * @param mongoLog
     */
    @Override
    public void addMongoLog(MongoLog mongoLog) {
        mongoTemplate.save(mongoLog);
    }

    @Override
    public List<MongoLog> queryLogAll() {

        List<MongoLog> all = mongoTemplate.findAll(MongoLog.class);
        return all;
    }
}
