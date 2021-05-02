package com.anatoliapark.nursinghome.domain;

import com.anatoliapark.nursinghome.domain.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {

    private String name,lastName;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean isActive = true;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date lastActivationDate;
    private Set<Long> roles;
    private Set<UserComponentContent> userComponentContents;

    public void addRole(Long id) {
        if (roles == null)
            roles = new HashSet<>();
        roles.add(id);
    }

    public void addUserComponentContent(UserComponentContent userComponentContent){
        if(userComponentContents == null)
            userComponentContents = new HashSet<>();
        userComponentContents.add(userComponentContent);
    }
}
