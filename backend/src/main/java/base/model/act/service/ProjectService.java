package base.model.act.service;


import base.model.act.mapper.ProjectMapper;
import base.model.act.pojo.Project;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Component
public class ProjectService extends ServiceImpl<ProjectMapper, Project> {

}
