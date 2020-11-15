package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.UserComponentEntity;
import com.anatoliapark.nursinghome.model.base.BaseConstantModel;
import com.anatoliapark.nursinghome.util.Mapper;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@EntityMapping(entityClass = UserComponentEntity.class)
public class UserComponent extends BaseConstantModel {

    @JsonIgnore
    private List<UserComponentContent> contents;

    public UserComponent(UserComponentEntity userComponentEntity) {
        super(userComponentEntity);
        this.setContents(Mapper.getModelList(userComponentEntity.getUserComponentContents()));
    }

    public List<UserComponentContent> getContents() {
        return contents;
    }

    public void setContents(List<UserComponentContent> contents) {
        this.contents = contents;
    }

}
