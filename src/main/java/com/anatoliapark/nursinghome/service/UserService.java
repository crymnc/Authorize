package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.model.base.User;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
}
