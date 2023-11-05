package base.model.design.pojo;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class ProjectEmitter extends SseEmitter {
    Long projectId;

    public ProjectEmitter(Long projectId, Long userId) {
        super(0L);
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }
}
