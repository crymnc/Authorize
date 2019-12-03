package com.anatoliapark.nursinghome.model.webpage;

import com.anatoliapark.nursinghome.model.auth.AuthorityOption;
import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name="webpage")
public class WebPage extends BaseConstantEntity {

    @OneToMany(
            mappedBy = "webPage",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE
    )
    private Collection<WebPageComponent> webPageComponents;

    @OneToMany(
            mappedBy = "webPage",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE
    )
    private Collection<AuthorityOption> authorityOptions;

    public Collection<WebPageComponent> getWebPageComponents() {
        return webPageComponents;
    }

    public void setWebPageComponents(Collection<WebPageComponent> webPageComponents) {
        this.webPageComponents = webPageComponents;
    }

    public Collection<AuthorityOption> getAuthorityOptions() {
        return authorityOptions;
    }

    public void setAuthorityOptions(Collection<AuthorityOption> authorityOptions) {
        this.authorityOptions = authorityOptions;
    }
}
