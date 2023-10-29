package base.model.design.pojo;

import base.model.design.enmu.ProjectRole;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("projects")
public class Project extends BaseEntity {
    @TableId(value = "project_id", type = IdType.AUTO)
    Long id;

    @TableField
    String projectName;

    /**
     * 项目管理员
     */
    @TableField(exist = false)
    ProjectRole rolesType;
}
