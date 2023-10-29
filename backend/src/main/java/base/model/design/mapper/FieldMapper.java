package base.model.design.mapper;

import base.model.design.pojo.Field;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

@Mapper
public interface FieldMapper extends BaseMapper<Field> {
    Collection<Field> queryLatestField(@Param("projectId") Long projectId);

}
