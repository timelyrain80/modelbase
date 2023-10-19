package base.model.act.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class VersionEntity implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.ASSIGN_ID)
    Long id;

    @CreatedBy
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;
}
