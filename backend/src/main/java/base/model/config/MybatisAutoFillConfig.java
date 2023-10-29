package base.model.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MybatisAutoFillConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        Long uid = -1L;
        try {
            uid = StpUtil.getLoginId(-1L);
        } catch (Exception e) {

        }
        setFieldValByName("createBy", uid, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        Long uid = -1L;
        try {
            uid = StpUtil.getLoginId(-1L);
        } catch (Exception e) {

        }
        setFieldValByName("updateBy", uid, metaObject);
    }
}
