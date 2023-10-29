package base.model.design.service;

import base.model.design.mapper.FieldMapper;
import base.model.design.pojo.Field;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional(rollbackFor = Exception.class)
@Component
public class FieldService extends VersionService<FieldMapper, Field>  {

    public void cascadeDelete(Collection<Long> tableIdList) {

    }
}
