package base.model.design.pojo;

import base.model.design.enmu.ProjectRole;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.EnumTypeHandler;

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

    @TableField(value = "role_type", typeHandler = EnumTypeHandler.class)
    private ProjectRole roleType;

    @TableField(value = "create_by")
    private Long createBy;

    @TableField(value = "create_time")
    private Date createTime;
}