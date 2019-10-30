package com.anatoliapark.nursinghome.model.auth;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;
import com.anatoliapark.nursinghome.model.webpage.WebPage;
import com.anatoliapark.nursinghome.model.webpage.WebPageComponent;

import javax.persistence.*;

@Entity
@Table(name = "authority_option")
public class AuthorityOption extends BaseEntityAudit {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id", referencedColumnName = "id")
    private Authority authority;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private WebPageComponent component;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private WebPage webPage;

    @Column(name = "active")
    private Boolean isActive;

    @Column(name = "visible")
    private Boolean isVisible;

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public WebPageComponent getComponent() {
        return component;
    }

    public void setComponent(WebPageComponent component) {
        this.component = component;
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
