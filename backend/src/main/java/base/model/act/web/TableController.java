package base.model.act.web;

import base.model.act.pojo.Table;
import base.model.act.service.TableService;
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


    @GetMapping("{projectId}")
    public ResponseEntity<Collection<Table>> listTable(@NotNull(message = "项目id不能为空") @PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(this.tableService.queryLatestTable(projectId));
    }


    @PostMapping()
    public ResponseEntity<Table> save(@RequestBody Table table) {
        return ResponseEntity.ok(this.tableService.saveVersion(table, Table::getTableId, Table::setTableId));
    }

    @PostMapping("delete")
    public ResponseEntity delete(@Validated @NotEmpty Collection<Long> tableIdList) {
        this.tableService.deleteTable(tableIdList);
        return ResponseEntity.ok().build();
    }
}
