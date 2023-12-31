package base.model.sys.service;

import base.model.sys.map.UserMap;
import base.model.sys.mapper.UserMapper;
import base.model.sys.pojo.LoginDto;
import base.model.sys.pojo.TokenDto;
import base.model.sys.pojo.User;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends ServiceImpl<UserMapper, User> {

    @Value("${base.model.salt:}")
    String salt;

    public TokenDto login(LoginDto dto) {
        Assert.notNull(dto.getUsername(), "用户名或密码错误");
        Assert.notNull(dto.getPassword(), "用户名或密码错误");
        List<User> userList = this.lambdaQuery()
                .eq(User::getEnabled, true)
                .eq(User::getUsername, dto.getUsername()).list();
        Assert.state(userList.size() == 1, "用户名或密码错误");
        Assert.state(SaSecureUtil.sha1(salt + dto.getPassword()).equals(userList.get(0).getPassword()), "用户名或密码错误");
        StpUtil.login(userList.get(0).getUserId(), dto.isRemember());

        return new TokenDto(StpUtil.getTokenValue(), UserMap.INSTANCE.toDto(userList.get(0)));
    }

}
