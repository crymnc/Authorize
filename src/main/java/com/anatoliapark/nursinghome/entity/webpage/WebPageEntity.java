package com.anatoliapark.nursinghome.entity.webpage;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.auth.AuthorityOptionEntity;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.WebPage;
import com.anatoliapark.nursinghome.model.base.BaseModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name="webpage")
@ModelMapping(modelClass = WebPage.class)
public class WebPageEntity extends BaseConstantEntity {

    @OneToMany(
            mappedBy = "webPage",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE
    )
    private Collection<WebPageComponentEntity> webPageComponents;

    @OneToMany(
            mappedBy = "webPage",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE
    )
    private Collection<AuthorityOptionEntity> authorityOptions;

    public WebPageEntity(BaseModel model) {
        super(model);
    }

    public WebPageEntity(){}

    public Collection<WebPageComponentEntity> getWebPageComponents() {
        return webPageComponents;
    }

    public void setWebPageComponents(Collection<WebPageComponentEntity> webPageComponents) {
        this.webPageComponents = webPageComponents;
    }

    public Collection<AuthorityOptionEntity> getAuthorityOptions() {
        return authorityOptions;
    }

    public void setAuthorityOptions(Collection<AuthorityOptionEntity> authorityOptions) {
        this.authorityOptions = authorityOptions;
    }

}
