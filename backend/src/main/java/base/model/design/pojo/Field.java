package base.model.design.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Field extends VersionEntity {
    Long fieldId;
    Long tableId;
    @TableField("field_label")
    String label;
    @TableField("field_code")
    String code;
    boolean pk = false;
    boolean notNull = false;
    boolean increment = false;
    @TableField("field_comment")
    String comment;
    String dataType;
    String defaultValue;
    String dict;
}
