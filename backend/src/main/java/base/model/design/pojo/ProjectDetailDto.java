package base.model.design.pojo;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class ProjectDetailDto {
    Project project;
    Collection<Table> tableList;
    Collection<Field> fieldList;
}
