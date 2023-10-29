package base.model.design.service;

import base.model.design.pojo.ActDto;
import base.model.design.pojo.ProjectEmitter;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
public class SubscribeService {
    Collection<ProjectEmitter> emitterList = new CopyOnWriteArrayList<>();

    @PostConstruct
    private void init() {
        ActDto<Object> heartBeat = ActDto.heartBeat();
        new Thread(() -> {
            // 发送一个心跳消息，将所有断连的emitter删除
            emitterList.forEach(t -> {
                try {
                    t.send(heartBeat);
                } catch (Exception e) {

                }
            });
            try {
                Thread.sleep(60 * 1000);
            } catch (Exception e) {
                log.error("sse 心跳线程退出", e);
                throw new RuntimeException(e);
            }
        });
    }

    public void addEmitter(ProjectEmitter e) {
        // 连接关闭时删除
        e.onCompletion(() -> this.removeEmitter(e));
        emitterList.add(e);
    }

    public void consumerMessage(ActDto actDto) {
        Long projectId = actDto.getProjectId();
        emitterList.stream()
                .filter(t -> projectId.equals(t.getProjectId()))
                .forEach(t -> {
                    try {
                        t.send(actDto);
                    } catch (Exception e) {
                    }
                });

    }

    private void removeEmitter(SseEmitter e) {
        if (emitterList.contains(e)) {
            emitterList.remove(e);
            log.info("emitter离线 {},剩余{}个活动连接", e.toString(), emitterList.size());
        }
    }
}
