package base.model.sys.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

/**
 * 系统用户
 */
@Data
public class UserDto {
    private Long userId;
    @Size(max = 180, message = "用户名最大长度要小于 180")
    private String username;

    /**
     * 昵称
     */
    @Size(max = 255, message = "昵称最大长度要小于 255")
    private String nickName;

    @Size(max = 180, message = "邮箱最大长度要小于 180")
    private String email;

    /**
     * 头像地址
     */
    @Size(max = 255, message = "头像地址最大长度要小于 255")
    private String avatarName;

    /**
     * 头像真实路径
     */
    @Size(max = 255, message = "头像真实路径最大长度要小于 255")
    private String avatarPath;

    private boolean enabled;

}