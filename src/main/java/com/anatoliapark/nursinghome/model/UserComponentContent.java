package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.auth.User;
import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;

import javax.persistence.*;

@Entity(name = "user_component_content")
public class UserComponentContent extends BaseEntityAudit {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private UserComponent component;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "content", nullable = false)
    private String content;

    public UserComponent getComponent() {
        return component;
    }

    public void setComponent(UserComponent component) {
        this.component = component;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
