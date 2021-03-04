package com.fh.api.entity.po;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "log")
public class Log {

    @Id
    private String id;

    private String name;

    private Date createdate;

    private Integer countlog;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getCountlog() {
        return countlog;
    }

    public void setCountlog(Integer countlog) {
        this.countlog = countlog;
    }
}
