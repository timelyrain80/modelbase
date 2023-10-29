package base.model.design.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Collection;

@Data
@TableName("m_table")
public class Table extends VersionEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long projectId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long tableId;

    @TableField("table_label")
    @NotNull(message = "请输入名称")
    String label;

    @TableField("table_code")
    @NotNull(message = "请输入代码")
    String code;

    @TableField("table_comment")
    @Size(max = 100, message = "注释长度不能超过100")
    String comment;

    @TableField(exist = false)
    Collection<Field> fieldList;
}
