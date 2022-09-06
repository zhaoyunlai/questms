package com.sdnu.iosclub.servicebase.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        String qrCode = UUID.randomUUID().toString().replace("-", "");
        this.setFieldValByName("qrCode", qrCode, metaObject);
        this.setFieldValByName("disabled", 0, metaObject);
        this.setFieldValByName("isDeleted", 0, metaObject);
//        this.setFieldValByName("deleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
