package base.model.design.web;

import base.model.design.pojo.ProjectDetailDto;
import base.model.design.pojo.Table;
import base.model.design.pojo.TableDeleteDto;
import base.model.design.pojo.TableDto;
import base.model.design.service.TableService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Validated
@RequestMapping("api/table")
public class TableController {

    @Resource
    TableService tableService;


    @PostMapping()
    public ResponseEntity<Table> save(@RequestBody TableDto table) {
        return ResponseEntity.ok(this.tableService.saveTable(table));
    }

    @PostMapping("delete")
    public ResponseEntity delete(@Validated @RequestBody TableDeleteDto dto) {
        this.tableService.updateForDeleteTable(dto.getProjectId(), dto.getTableIdList());
        return ResponseEntity.ok().build();
    }
}
