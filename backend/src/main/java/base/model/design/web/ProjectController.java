package base.model.design.web;

import base.model.design.pojo.Project;
import base.model.design.pojo.ProjectDetailDto;
import base.model.design.pojo.ProjectEmitter;
import base.model.design.service.ProjectService;
import base.model.common.ModelConstants;
import base.model.design.service.SubscribeService;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ModelConstants.API_PREFIX + "/project")
public class ProjectController {
    @Resource
    final ProjectService service;
    final SubscribeService subscribeService;

    @PostMapping("save")
    ResponseEntity<Boolean> save(@Validated @RequestBody Project project) {
        service.saveOrUpdate(project);
        return ResponseEntity.ok(true);
    }

    @PostMapping("delete")
    ResponseEntity<Boolean> delete(@Validated @NotBlank(message = "请选择需要删除的数据") @RequestBody List<Long> idList) {
        // todo 管理员删除全部的项目
        // 检查 只能删除自己的项目
        service.lambdaUpdate()
                .in(Project::getId, idList)
                .eq(Project::getCreateBy, StpUtil.getLoginId())
                .remove();
        return ResponseEntity.ok(true);
    }

    @PostMapping("my-project")
    ResponseEntity<List<Project>> queryMyProject() {
        return ResponseEntity.ok(service.queryProject());
    }

    @GetMapping("{id}")
    ResponseEntity<ProjectDetailDto> detail(@PathVariable("id") Long projectId) {
        Assert.state(projectId != null, "参数不合法");
        return ResponseEntity.ok(service.queryDetail(projectId));
    }

    @GetMapping(value = "event/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sse(@PathVariable("id") Long projectId, HttpServletRequest request) {
        Long userId = StpUtil.getLoginId(-1L);
        ProjectEmitter s = new ProjectEmitter(projectId, userId);
        subscribeService.addEmitter(s);
        return s;
    }

}
