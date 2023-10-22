package base.model.sys.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    @NotNull(message = "用户名或密码错误")
    String username;
    @NotNull(message = "用户名或密码错误")
    String password;
    boolean remember = false;
}
