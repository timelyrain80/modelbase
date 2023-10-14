package base.model.act.pojo;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class ProjectEmitter extends SseEmitter {
    Long projectId;

    public ProjectEmitter(Long projectId) {
        super(0L);
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }
}
