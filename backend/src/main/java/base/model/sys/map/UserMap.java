package base.model.sys.map;


import base.model.sys.pojo.User;
import base.model.sys.pojo.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMap {
    UserMap INSTANCE = Mappers.getMapper(UserMap.class);

    UserDto toDto(User u);

    User toUser(UserDto dto);
}
