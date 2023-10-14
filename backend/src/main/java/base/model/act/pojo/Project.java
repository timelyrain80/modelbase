package base.model.act.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("m_project")
public class Project extends BaseEntity {
    @TableId(value = "project_id", type = IdType.AUTO)
    Long id;

    @TableField
    String projectName;
}
