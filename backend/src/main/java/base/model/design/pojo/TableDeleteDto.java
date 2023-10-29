package base.model.design.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TableDeleteDto {
    @NotNull
    Long projectId;
    @NotNull
    List<Long> tableIdList;
}
