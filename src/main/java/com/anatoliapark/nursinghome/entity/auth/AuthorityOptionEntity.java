package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.base.BaseEntityAudit;
import com.anatoliapark.nursinghome.entity.webpage.WebPageComponentEntity;
import com.anatoliapark.nursinghome.entity.webpage.WebPageEntity;
import com.anatoliapark.nursinghome.model.AuthorityOption;

import javax.persistence.*;

@Entity(name = "authority_option")
@ModelMapping(modelClass = AuthorityOption.class)
public class AuthorityOptionEntity extends BaseEntityAudit {

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH} )
    @JoinColumn(name = "authority_id", referencedColumnName = "id")
    private AuthorityEntity authority;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH} )
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private WebPageComponentEntity component;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH} )
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private WebPageEntity webPage;

    @Column(name = "active")
    private Boolean isActive;

    @Column(name = "visible")
    private Boolean isVisible;

    public AuthorityOptionEntity(AuthorityOption authorityOption) {
        super(authorityOption);
        this.setActive(authorityOption.getActive());
        this.setVisible(authorityOption.getVisible());
        this.setAuthority(new AuthorityEntity(authorityOption.getAuthority()));
        this.setComponent(new WebPageComponentEntity(authorityOption.getWebPageComponent()));
        this.setWebPage(new WebPageEntity(authorityOption.getWebPage()));
    }

    public AuthorityOptionEntity(){}

    public AuthorityEntity getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityEntity authority) {
        this.authority = authority;
    }

    public WebPageComponentEntity getComponent() {
        return component;
    }

    public void setComponent(WebPageComponentEntity component) {
        this.component = component;
    }

    public WebPageEntity getWebPage() {
        return webPage;
    }

    public void setWebPage(WebPageEntity webPage) {
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
