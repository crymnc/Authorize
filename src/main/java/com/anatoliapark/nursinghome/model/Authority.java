package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.auth.AuthorityEntity;
import com.anatoliapark.nursinghome.model.base.BaseConstantModel;
import com.anatoliapark.nursinghome.util.Mapper;

import java.util.List;
@EntityMapping(entityClass = AuthorityEntity.class)
public class Authority extends BaseConstantModel {

    private List<AuthorityOption> authorityOptions;

    public Authority(AuthorityEntity authorityEntity) {
        super(authorityEntity);
        setAuthorityOptions(Mapper.getModelList(authorityEntity.getAuthorityOptions()));
    }

    public Authority(){super();}

    public List<AuthorityOption> getAuthorityOptions() {
        return authorityOptions;
    }

    public void setAuthorityOptions(List<AuthorityOption> authorityOptions) {
        this.authorityOptions = authorityOptions;
    }
}
