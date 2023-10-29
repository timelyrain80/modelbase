package base.model.design.web;


import base.model.design.pojo.ProjectEmitter;
import base.model.design.service.SubscribeService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class SseController {
    @Resource
    SubscribeService subscribeService;

    @GetMapping(value = "api/project/sse/{projectId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sse(@PathVariable("projectId") Long projectId) {
        SseEmitter s = new SseEmitter(0L);
        subscribeService.addEmitter(new ProjectEmitter(projectId));
        return s;
    }
}
