package base.model.act.service;


import base.model.act.enmu.ProjectRole;
import base.model.act.mapper.ProjectMapper;
import base.model.act.pojo.Project;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Component
public class ProjectService extends ServiceImpl<ProjectMapper, Project> {
    @Resource
    ProjectUserService projectUserService;

    @Override
    public boolean saveOrUpdate(Project project) {
        boolean create = false;
        if (project.getId() == null) {
            project.setCreateBy(String.valueOf(StpUtil.getLoginId()));
            project.setCreateTime(new Timestamp(new Date().getTime()));
            create = true;
        } else {
            project.setUpdateBy(String.valueOf(StpUtil.getLoginId()));
            project.setUpdateTime(new Timestamp(new Date().getTime()));
        }

        super.saveOrUpdate(project);
        if (create) {
            projectUserService.add(project.getId(), ProjectRole.ADMIN);
        }
        return true;
    }

    public List<Project> queryProject() {
        return getBaseMapper().queryProject(StpUtil.getLoginId(0L));
    }
}
