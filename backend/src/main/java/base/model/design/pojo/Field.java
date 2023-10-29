package base.model.design.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("m_field")
public class Field extends VersionEntity {
    Long fieldId;
    Long tableId;
    String label;
    String code;
    boolean pk = false;
    boolean notNull = false;
    boolean increment = false;
    String comment;
    String dataType;
    String defaultValue;
    String dict;
}
