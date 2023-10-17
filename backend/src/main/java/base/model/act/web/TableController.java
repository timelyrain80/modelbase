package base.model.act.web;

import me.zhengjie.modules.modeler.domain.Table;
import me.zhengjie.modules.modeler.service.TableService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
@RequestMapping("api/table")
public class TableController {

    @Resource
    TableService tableService;


    @GetMapping("{projectId}")
    public ResponseEntity<Collection<Table>> listTable(@Validated @NotNull(message = "项目id不能为空") @PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(this.tableService.queryLatestTable(projectId));
    }


    @PostMapping()
    public ResponseEntity<Table> save(@Valid @RequestBody Table table) {
        return ResponseEntity.ok(this.tableService.saveVersion(table, Table::getTableId, Table::setTableId));
    }

    @PostMapping("delete")
    public ResponseEntity delete(@Validated @NotEmpty Collection<Long> tableIdList){
        this.tableService.deleteTable(tableIdList);
        return ResponseEntity.ok().build();
    }
}