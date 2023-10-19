package base.model.act.web;

import base.model.act.pojo.Project;
import base.model.act.service.ProjectService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController {
    @Resource
    private ProjectService service;

    @PostMapping("save")
    ResponseEntity<Boolean> save(@Validated @RequestBody Project project) {
        service.saveOrUpdate(project);
        return ResponseEntity.ok(true);
    }

    @PostMapping("delete")
    ResponseEntity<Boolean> delete(@Validated @NotBlank(message = "请选择需要删除的数据") @RequestBody List<Long> idList) {
        service.removeBatchByIds(idList);
        return ResponseEntity.ok(true);
    }

    @PostMapping("my-project")
    ResponseEntity<List<Project>> queryMyProject() {
        return ResponseEntity.ok(service.lambdaQuery().eq(Project::getCreateBy, "SecurityUtils.getCurrentUserId()").list());
    }
}
