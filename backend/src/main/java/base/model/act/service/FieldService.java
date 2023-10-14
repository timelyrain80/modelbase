package base.model.act.service;

import base.model.act.mapper.FieldMapper;
import base.model.act.pojo.Field;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional(rollbackFor = Exception.class)
@Component
public class FieldService extends VersionService<FieldMapper, Field>  {

    public void cascadeDelete(Collection<Long> tableIdList) {

    }
}
