package com.anatoliapark.nursinghome.domain;

import com.anatoliapark.nursinghome.domain.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {

    private String name,lastName,username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean isActive = true;
    private Date lastActivationDate;
    private Set<Role> roles;
    private Set<UserComponentContent> userComponentContents;

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority() {
        return getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName())).collect(Collectors.toList());
    }
}
