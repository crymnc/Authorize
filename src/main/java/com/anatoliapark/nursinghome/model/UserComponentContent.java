package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.UserComponentContentEntity;
import com.anatoliapark.nursinghome.model.base.BaseModel;

@EntityMapping(entityClass = UserComponentContentEntity.class)
public class UserComponentContent extends BaseModel {

    private String content;

    private UserComponent component;

    private Long userId;

    public UserComponentContent(UserComponentContentEntity userComponentContentEntity) {
        super(userComponentContentEntity);
        this.setComponent(new UserComponent(userComponentContentEntity.getComponent()));
        this.setUserId(userComponentContentEntity.getUser().getId());
        this.setContent(userComponentContentEntity.getContent());
    }

    public UserComponentContent(){super();}

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
