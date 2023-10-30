package base.model.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig extends SimpleModule {
    public JsonConfig() {
        //super(JsonModuleConfig.class.getName());
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
    }
}