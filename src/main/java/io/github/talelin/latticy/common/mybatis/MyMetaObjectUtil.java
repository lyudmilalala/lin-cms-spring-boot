package io.github.talelin.latticy.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectUtil implements MetaObjectHandler {
    /**
     * @description TODO
     * @author Yuchen Sun
     * @createDate 2021-08-16
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 属性名称，不是字段名称
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
