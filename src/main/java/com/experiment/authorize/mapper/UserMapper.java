package com.experiment.authorize.mapper;

import com.experiment.authorize.domain.User;
import com.experiment.authorize.entity.UserComponentContentEntity;
import com.experiment.authorize.entity.auth.RoleEntity;
import com.experiment.authorize.entity.auth.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserComponentContentMapper.class})
public abstract class UserMapper {
    @Autowired
    private UserComponentContentMapper userComponentContentMapper;

    @Mappings({
            @Mapping(target = "roles", source = "userEntity.roles")
    })
    public abstract User toDomain(UserEntity userEntity);

    Set<Long> roleEntityToId(Set<RoleEntity> roles) {
        Set<Long> ids = new HashSet<>();
        roles.forEach(roleEntity -> ids.add(roleEntity.getId()));
        return ids;
    }

    Set<RoleEntity> idsToRoleEntity(Set<Long> ids) {
        Set<RoleEntity> roles = new HashSet<>();
        ids.forEach(id -> {
            RoleEntity role = new RoleEntity();
            role.setId(id);
            roles.add(role);
        });
        return roles;
    }

    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setDiscontinueDate(user.getDiscontinueDate());
        userEntity.setCreatedAt(user.getCreatedAt());
        userEntity.setCreatedBy(user.getCreatedBy());
        userEntity.setUpdatedAt(user.getUpdatedAt());
        userEntity.setUpdatedBy(user.getUpdatedBy());
        userEntity.setName(user.getName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setActive(user.isActive());
        userEntity.setLastActivationDate(user.getLastActivationDate());
        userEntity.setRoles(idsToRoleEntity(user.getRoles()));
        userEntity.setUserComponentContents(user.getUserComponentContents()
                .stream()
                .map(userComponentContent ->
                {
                    UserComponentContentEntity userComponentContentEntity = userComponentContentMapper.toEntity(userComponentContent);
                    userComponentContentEntity.setUser(userEntity);
                    return userComponentContentEntity;
                })
                .collect(Collectors.toSet()));
        return userEntity;
    }

    public abstract List<User> toDomainList(List<UserEntity> userEntityList);

    public abstract List<UserEntity> toEntityList(List<User> userList);

}
