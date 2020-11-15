package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.UserComponentContentEntity;
import com.anatoliapark.nursinghome.model.base.BaseModel;

@EntityMapping(entityClass = UserComponentContentEntity.class)
public class UserComponentContent extends BaseModel {

    private String content;

    private UserComponent component;

    private User user;

    public UserComponentContent(UserComponentContentEntity userComponentContentEntity) {
        super(userComponentContentEntity);
        this.setComponent(new UserComponent(userComponentContentEntity.getComponent()));
        this.setUser(new User(userComponentContentEntity.getUser()));
        this.setContent(userComponentContentEntity.getContent());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
}
