package com.anatoliapark.nursinghome.entity;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.entity.base.BaseEntityAudit;
import com.anatoliapark.nursinghome.model.UserComponentContent;

import javax.persistence.*;

@Entity(name = "user_component_content")
@ModelMapping(modelClass = UserComponentContent.class)
public class UserComponentContentEntity extends BaseEntityAudit{

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private UserComponentEntity component;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "content", nullable = false)
    private String content;

    public UserComponentContentEntity(UserComponentContent userComponentContent){
        super(userComponentContent);
        this.setContent(userComponentContent.getContent());
    }

    public UserComponentContentEntity(){}

    public UserComponentEntity getComponent() {
        return component;
    }

    public void setComponent(UserComponentEntity component) {
        this.component = component;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
