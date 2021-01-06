package com.anatoliapark.nursinghome.mapper;

import com.anatoliapark.nursinghome.domain.User;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserComponentContentMapper.class})
public interface UserMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);

    List<User> toDomainList(List<UserEntity> userEntityList);

    List<UserEntity> toEntityList(List<User> userList);

}
