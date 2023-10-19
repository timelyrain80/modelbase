package base.model.sys.web;

import base.model.sys.pojo.TokenDto;
import base.model.sys.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class UserController {
    final UserService service;


    @PostMapping("token")
    ResponseEntity<TokenDto> login(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.login(body.get("username"), body.get("password")));
    }
}
