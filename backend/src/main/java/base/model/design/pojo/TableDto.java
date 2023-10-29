package base.model.design.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TableDto extends Table {
    Long projectId;
    Field fieldList;
    /**
     * 字段保存的是Field.id
     */
    List<String> deleteFieldIdList;
}
