package com.experiment.accounting.proxy.authorize;

import com.experiment.accounting.proxy.authorize.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="authorize")
//Can not be added ribbon to Authorize client because it is authentication server, and it should be one instance
//@RibbonClient(name="authorize")
public interface AuthorizeProxy {

    @GetMapping("/api/users/username/{username}")
    User getUserByUsername(@PathVariable(name = "username") String username);

    @GetMapping("/api/users/user-id/{user-id}")
    User getUserById(@PathVariable(name = "user-id") Long userId);
}
