package com.experiment.authorize.mapper;

import com.experiment.authorize.domain.UserComponentContent;
import com.experiment.authorize.entity.UserComponentContentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserComponentContentMapper {

    @Mappings({
            @Mapping(target = "componentId", source = "userComponentContentEntity.component.id"),
            @Mapping(target = "componentName", source = "userComponentContentEntity.component.name"),
            @Mapping(target = "userId", source = "userComponentContentEntity.user.id")
    })
    UserComponentContent toDomain(UserComponentContentEntity userComponentContentEntity);

    @Mappings({
            @Mapping(target = "component.id", source = "userComponentContent.componentId"),
            @Mapping(target = "user.id", source = "userComponentContent.userId")
    })
    UserComponentContentEntity toEntity(UserComponentContent userComponentContent);
}
