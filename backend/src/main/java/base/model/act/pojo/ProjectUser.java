package base.model.act.pojo;

import base.model.act.enmu.ProjectRole;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.EnumTypeHandler;

import java.math.BigInteger;
import java.util.Date;

@Data
@TableName(value = "project_user")
public class ProjectUser {
    @TableField(value = "id")
    private Long id;

    @TableField(value = "project_id")
    private Long projectId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "roles", typeHandler = EnumTypeHandler.class)
    private ProjectRole roles;

    @TableField(value = "create_by")
    private Long createBy;

    @TableField(value = "create_time")
    private Date createTime;
}