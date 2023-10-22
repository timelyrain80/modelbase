package base.model.sys.web;

import base.model.common.ModelConstants;
import base.model.sys.pojo.LoginDto;
import base.model.sys.pojo.TokenDto;
import base.model.sys.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(ModelConstants.API_PREFIX)
@Validated
public class UserController {
    final UserService service;

    @PostMapping("token")
    ResponseEntity<TokenDto> login(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(service.login(dto));
    }
}
