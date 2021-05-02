package com.anatoliapark.nursinghome.mapper;

import com.anatoliapark.nursinghome.domain.base.BaseConstantModel;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.service.ConstantService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ConstantMapper {

    @Autowired
    ConstantService constantService;

    public <X extends BaseConstantModel, Y extends BaseConstantEntity> X toModel(Y constantEntity) {
        BaseConstantModel model = new BaseConstantModel();
        model.setId(constantEntity.getId());
        model.setName(constantEntity.getName());
        model.setDsc(constantEntity.getDsc());
        return (X) model;
    }

    public <X extends BaseConstantEntity, Y extends BaseConstantModel> X toEntity(Y constantModel, Class<X> clazz) {
        X entity = null;
        try {
            entity = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        entity.setId(constantModel.getId());
        entity.setName(constantModel.getName());
        entity.setDsc(constantModel.getDsc());
        return entity;
    }
}
