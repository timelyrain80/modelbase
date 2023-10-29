package base.model.design.service;

import base.model.config.RedisConfig;
import base.model.design.mapper.FieldMapper;
import base.model.design.pojo.ActDto;
import base.model.design.pojo.Field;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Transactional(rollbackFor = Exception.class)
@Component
public class FieldService extends VersionService<FieldMapper, Field> {
    @Resource
    RedisTemplate redisTemplate;

    public void updateForDeleteField(Long projectId, List<String> deleteFieldIdList) {
        if(deleteFieldIdList == null || deleteFieldIdList.isEmpty()){
            return;
        }
        // 更新删除标记
        this.lambdaUpdate().set(Field::isDeleted, true)
                .in(Field::getId, deleteFieldIdList).update();

        // 查询field对象
        List<Field> fieldList = this.lambdaQuery()
                .in(Field::getId, deleteFieldIdList)
                .list();

        // 广播 删除通知
        fieldList.forEach(f -> {
            redisTemplate.convertAndSend(RedisConfig.CHANGE_SUBSCRBE, ActDto.del(projectId, f));
        });
    }

    @Override
    public Field saveVersion(Long projectId, Field entity, Function<Field, Long> getter, BiConsumer<Field, Long> setter) {
        //
        return super.saveVersion(projectId, entity, getter, setter);
    }

    public Collection<Field> queryLatestField(Long projectId) {
        return this.getBaseMapper().queryLatestField(projectId);
    }
}
