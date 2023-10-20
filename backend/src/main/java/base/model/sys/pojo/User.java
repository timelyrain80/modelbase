package base.model.sys.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 系统用户
 */
@Data
@TableName(value = "sys_user")
public class User {
    /**
     * ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @NotNull(message = "ID不能为null")
    private Long userId;

    /**
     * 部门名称
     */
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @Size(max = 180, message = "用户名最大长度要小于 180")
    private String username;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    @Size(max = 255, message = "昵称最大长度要小于 255")
    private String nickName;

    /**
     * 性别
     */
    @TableField(value = "gender")
    @Size(max = 2, message = "性别最大长度要小于 2")
    private String gender;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    @Size(max = 255, message = "手机号码最大长度要小于 255")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @Size(max = 180, message = "邮箱最大长度要小于 180")
    private String email;

    /**
     * 头像地址
     */
    @TableField(value = "avatar_name")
    @Size(max = 255, message = "头像地址最大长度要小于 255")
    private String avatarName;

    /**
     * 头像真实路径
     */
    @TableField(value = "avatar_path")
    @Size(max = 255, message = "头像真实路径最大长度要小于 255")
    private String avatarPath;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @Size(max = 255, message = "密码最大长度要小于 255")
    private String password;

    /**
     * 是否为admin账号
     */
    @TableField(value = "is_admin")
    private Boolean isAdmin;

    /**
     * 状态：1启用、0禁用
     */
    @TableField(value = "enabled")
    private Boolean enabled;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @Size(max = 255, message = "创建者最大长度要小于 255")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @Size(max = 255, message = "更新者最大长度要小于 255")
    private String updateBy;

    /**
     * 修改密码的时间
     */
    @TableField(value = "pwd_reset_time")
    private Date pwdResetTime;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}