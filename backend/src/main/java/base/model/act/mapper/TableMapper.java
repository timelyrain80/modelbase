package base.model.act.mapper;

import base.model.act.pojo.Table;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

@Mapper
public interface TableMapper extends BaseMapper<Table> {
    /**
     * 查询最新版本的Table定义
     *
     * @param projectId
     * @return
     */
    Collection<Table> queryLatestTable(@Param("projectId") Long projectId);
}
