package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.model.base.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {

    User getActiveUser(String username);

    User registerNewUser(User user);
}
