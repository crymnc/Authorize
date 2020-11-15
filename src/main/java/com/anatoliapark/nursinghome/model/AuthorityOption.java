package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.auth.AuthorityOptionEntity;
import com.anatoliapark.nursinghome.model.base.BaseModel;

@EntityMapping(entityClass = AuthorityOptionEntity.class)
public class AuthorityOption extends BaseModel {

    private Authority authority;
    private WebPageComponent webPageComponent;
    private WebPage webPage;
    private Boolean isActive, isVisible;

    public AuthorityOption(AuthorityOptionEntity authorityOptionEntity) {
        super(authorityOptionEntity);
        this.setWebPageComponent(new WebPageComponent(authorityOptionEntity.getComponent()));
        this.setWebPage(new WebPage(authorityOptionEntity.getWebPage()));
        this.setAuthority(new Authority(authorityOptionEntity.getAuthority()));
        this.setVisible(authorityOptionEntity.getVisible());
        this.setActive(authorityOptionEntity.getVisible());
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public WebPageComponent getWebPageComponent() {
        return webPageComponent;
    }

    public void setWebPageComponent(WebPageComponent webPageComponent) {
        this.webPageComponent = webPageComponent;
    }

    public WebPage getWebPage() {
        return webPage;
    }

    public void setWebPage(WebPage webPage) {
        this.webPage = webPage;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }
}
