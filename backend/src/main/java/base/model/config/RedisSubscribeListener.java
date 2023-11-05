package base.model.config;

import base.model.design.pojo.ActDto;
import base.model.design.service.SubscribeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisSubscribeListener implements MessageListener {

    @Resource
    RedisTemplate<Object, Object> redisTemplate;
    @Resource
    SubscribeService service;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        ActDto actDto = (ActDto) redisTemplate.getValueSerializer().deserialize(message.getBody());
        log.debug("redis 监听到：{}", actDto);
        service.consumerMessage(actDto);
    }
}
