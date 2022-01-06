package com.experiment.accounting.mapper;

import com.experiment.accounting.domain.base.BaseConstantModel;
import com.experiment.accounting.entity.base.BaseConstantEntity;
import org.mapstruct.Mapper;

import java.lang.reflect.InvocationTargetException;

@Mapper(componentModel = "spring")
public class ConstantMapper {

    public <X extends BaseConstantModel, Y extends BaseConstantEntity> X toModel(Y constantEntity) {
        BaseConstantModel model = new BaseConstantModel();
        model.setId(constantEntity.getId());
        model.setName(constantEntity.getName());
        model.setDsc(constantEntity.getDsc());
        model.setDiscontinueDate(constantEntity.getDiscontinueDate());
        model.setCreatedAt(constantEntity.getCreatedAt());
        model.setCreatedBy(constantEntity.getCreatedBy());
        return (X) model;
    }

    public <X extends BaseConstantEntity, Y extends BaseConstantModel> X toEntity(Y constantModel, Class<X> clazz) {
        X entity = null;
        try {
            entity = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        entity.setId(constantModel.getId());
        entity.setName(constantModel.getName());
        entity.setDsc(constantModel.getDsc());
        entity.setCreatedAt(constantModel.getCreatedAt());
        entity.setDiscontinueDate(constantModel.getDiscontinueDate());
        entity.setCreatedBy(constantModel.getCreatedBy());
        return entity;
    }
}
