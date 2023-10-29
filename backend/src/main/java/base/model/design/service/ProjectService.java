package base.model.design.service;


import base.model.design.enmu.ProjectRole;
import base.model.design.mapper.ProjectMapper;
import base.model.design.pojo.Project;
import base.model.design.pojo.ProjectDetailDto;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Component
public class ProjectService extends ServiceImpl<ProjectMapper, Project> {
    @Resource
    ProjectUserService projectUserService;
    @Resource
    TableService tableService;
    @Resource
    FieldService fieldService;

    @Override
    public boolean saveOrUpdate(Project project) {
        boolean create = false;
        super.saveOrUpdate(project);
        if (create) {
            projectUserService.add(project.getId(), ProjectRole.ADMIN);
        }
        return true;
    }

    public List<Project> queryProject() {
        return getBaseMapper().queryProject(StpUtil.getLoginId(0L));
    }

    public ProjectDetailDto queryDetail(Long projectId) {
        ProjectDetailDto dto = new ProjectDetailDto();
        dto.setProject(this.getById(projectId));
        dto.setTableList(tableService.queryLatestTable(projectId));
        dto.setFieldList(fieldService.queryLatestField(projectId));
        return dto;
    }
}
