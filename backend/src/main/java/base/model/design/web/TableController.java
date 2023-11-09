package base.model.design.web;

import base.model.design.pojo.Field;
import base.model.design.pojo.Table;
import base.model.design.pojo.TableDeleteDto;
import base.model.design.pojo.TableDto;
import base.model.design.service.FieldService;
import base.model.design.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("api/table")
public class TableController {

    final TableService tableService;
    final FieldService fieldService;
    final RedisTemplate redisTemplate;

    @PostMapping()
    public ResponseEntity<Table> save(@RequestBody TableDto table) {
        return ResponseEntity.ok(this.tableService.saveTable(table));
    }

    @PostMapping("delete")
    public ResponseEntity delete(@Validated @RequestBody TableDeleteDto dto) {
        this.tableService.updateForDeleteTable(dto.getProjectId(), dto.getTableIdList());
        return ResponseEntity.ok().build();
    }

    @PostMapping("field")
    public ResponseEntity<List<Field>> queryFieldByTableIdList(Collection<Long> tableIdList) {
        List<Field> dataList = fieldService.queryLatestField(tableIdList);
        return ResponseEntity.ok(dataList);
    }

    @PostMapping("field/save")
    public ResponseEntity<Field> saveField(Field field) {
        fieldService.saveVersion(field.getProjectId(), field, Field::getFieldId, Field::setFieldId);
        return ResponseEntity.ok(field);
    }

    @GetMapping("field/editing")
    public ResponseEntity<Boolean> fieldEditing(@RequestParam Long fieldId) {
//        redisTemplate.convertAndSend(RedisConfig.CHANGE_SUBSCRBE, ActDto.save())
        return ResponseEntity.ok(true);
    }
}
