package base.model.design.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class TableDto {
    Long projectId;
    Long tableId;
    Table table;
    List<Field> fieldList;
    /**
     * 字段保存的是Field.id
     */
    List<String> deleteFieldIdList;
}
