package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.entity.webpage.WebPageEntity;
import com.anatoliapark.nursinghome.model.base.BaseConstantModel;

import java.util.List;

@EntityMapping(entityClass = WebPageEntity.class)
public class WebPage extends BaseConstantModel {

    private List<WebPageComponent> webPageComponents;
    private List<AuthorityOption> authorityOptions;

    public WebPage(BaseConstantEntity baseConstantEntity) {
        super(baseConstantEntity);
    }

    public List<WebPageComponent> getWebPageComponents() {
        return webPageComponents;
    }

    public void setWebPageComponents(List<WebPageComponent> webPageComponents) {
        this.webPageComponents = webPageComponents;
    }

    public List<AuthorityOption> getAuthorityOptions() {
        return authorityOptions;
    }

    public void setAuthorityOptions(List<AuthorityOption> authorityOptions) {
        this.authorityOptions = authorityOptions;
    }
}
