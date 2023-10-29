package base.model.design.service;

import base.model.design.pojo.VersionEntity;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class VersionService<M extends BaseMapper<T>, T extends VersionEntity> extends ServiceImpl<M, T> {
    public T saveVersion(Long projectId, T entity, Function<T, Long> getter, BiConsumer<T, Long> setter) {
        entity.setId(null);
        if (Objects.isNull(getter.apply(entity))) {
            setter.accept(entity, IdUtil.getSnowflakeNextId());
        }
        entity.setProjectId(projectId);
        this.save(entity);
        return entity;
    }

}
