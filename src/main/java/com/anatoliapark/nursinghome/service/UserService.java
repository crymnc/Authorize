package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.model.auth.User;

public interface UserService {

    User getActiveUser(String username);

    User registerNewUser(User user);
}
