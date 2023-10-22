package base.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModelerApplication {
    public static void main(String[] args) {
        System.out.println("系统启动");
        SpringApplication.run(ModelerApplication.class, args);
    }
}
