package com.skopei.demo.user;

import com.skopei.demo.abstraction.AbstractCtrl;
import com.skopei.demo.abstraction.ICRUD;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name="User")
public class UserCtrl extends AbstractCtrl<UserProfile> {

    public UserCtrl(@Qualifier("UserDAO") ICRUD<UserProfile> DAO) { super(DAO); }
}