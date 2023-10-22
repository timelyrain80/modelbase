package base.model.act.service;

import base.model.act.enmu.ProjectRole;
import base.model.act.mapper.ProjectUserMapper;
import base.model.act.pojo.ProjectUser;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ProjectUserService extends ServiceImpl<ProjectUserMapper, ProjectUser> {

    public void add(Long projectId, ProjectRole role) {
        // 检查
        Long count = this.lambdaQuery().eq(ProjectUser::getProjectId, projectId)
                .eq(ProjectUser::getUserId, StpUtil.getLoginId())
                .count();
        Assert.state(count == 0, "用户已在项目内");
        //  插入管理员角色
        ProjectUser pu = new ProjectUser();
        pu.setProjectId(projectId);
        pu.setUserId((Long) StpUtil.getLoginId());
        pu.setCreateBy(pu.getUserId());
        pu.setRoles(role);

        this.save(pu);
    }

    public void changeRole(Long pk, ProjectRole newRole) {
        ProjectUser pu = this.getById(pk);
        Assert.notNull(pu, "授权信息不存在");
        // 检查
        if (ProjectRole.ADMIN.equals(pu.getRoles()) && !ProjectRole.ADMIN.equals(newRole)) {
            // 如果从某人从管理员 调整为非管理员时，必须保证管理员大于1
            Long count = this.lambdaQuery().eq(ProjectUser::getProjectId, pu.getProjectId())
                    .eq(ProjectUser::getRoles, ProjectRole.ADMIN)
                    .count();
            Assert.state(count > 1, "项目至少要设置一个管理员");
        }
        pu.setRoles(newRole);
        this.updateById(pu);
    }
}
