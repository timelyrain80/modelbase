package base.model.design.service;

import base.model.common.exception.BadRequestException;
import base.model.design.mapper.TableMapper;
import base.model.design.pojo.ActDto;
import base.model.design.pojo.Field;
import base.model.design.pojo.Table;
import base.model.design.pojo.TableDto;
import cn.hutool.core.lang.Assert;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static base.model.config.RedisConfig.CHANGE_SUBSCRBE;


@Transactional(rollbackFor = Exception.class)
@Component
public class TableService extends VersionService<TableMapper, Table> {
    @Resource
    FieldService fieldService;
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    public Collection<Table> queryLatestTable(Long projectId) {
        return getBaseMapper().queryLatestTable(projectId);
    }

    public Collection<Table> queryHisTable(Long tableId) {
        return this.lambdaQuery().eq(Table::getTableId, tableId).orderByDesc(Table::getTableId).list();
    }

    public void cascadeDelete(Collection<Long> projectIdList) {
        if (projectIdList == null || projectIdList.isEmpty()) {
            return;
        }
        Collection<Long> tableIdList = this.lambdaQuery().in(Table::getProjectId, projectIdList)
                .list().stream()
                .map(Table::getTableId).collect(Collectors.toSet());
        this.deleteTable(tableIdList);
    }

    public void deleteTable(Collection<Long> tableIdList) {
        if (tableIdList == null || tableIdList.isEmpty()) {
            return;
        }
        // 删除表
        this.lambdaUpdate().in(Table::getTableId, tableIdList)
                .set(Table::isDeleted, true)
                .update();
        // 获取更新后的table信息
        List<Table> tableList = this.lambdaQuery().in(Table::getTableId, tableIdList).list();
        tableList.forEach(t -> {
            // 广播变动
            redisTemplate.convertAndSend(CHANGE_SUBSCRBE, ActDto.save(t.getProjectId(), t));
        });
    }

    /**
     * 新增一个table版本
     */
    public Table saveVersion(Table entity, Function<Table, Long> getter, BiConsumer<Table, Long> setter) {
        // 检查
        long sameCount = this.lambdaQuery()
                .eq(Table::getProjectId, entity.getProjectId())
                .ne(entity.getTableId() != null, Table::getTableId, entity.getTableId())
                .and((c1) -> c1.eq(Table::getLabel, entity.getLabel()).or((c2) -> c2.eq(Table::getCode, entity.getCode())))
                .count();
        Assert.isTrue(sameCount == 0, () -> new BadRequestException("表名称不能为重复"));

        Table table = super.saveVersion(entity, getter, setter);
        redisTemplate.convertAndSend(CHANGE_SUBSCRBE, ActDto.save(table.getProjectId(), table));
        return table;
    }

    public Table saveTable(TableDto table) {
        // 判断并保存table
        Table entity = new Table();
        BeanUtils.copyProperties(table, entity);
        this.saveVersion(entity, Table::getTableId, Table::setTableId);
        // 判断并保存field

        fieldService.saveVersion(table.getProjectId(), table.getFieldList(), Field::getFieldId, Field::setFieldId);
        // 删除字段
        fieldService.updateToDeleted(table.getProjectId(), table.getDeleteFieldIdList());
        return null;
    }
}
