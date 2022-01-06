package com.experiment.accounting.proxy.authorize.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private Long createdBy,updatedBy;
    private Date discontinueDate;
    private Date createdAt,updatedAt;
    private String name,lastName;
    private String username;
    private String password;
    private boolean isActive = true;
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
