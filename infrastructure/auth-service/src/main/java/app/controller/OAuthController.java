package app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by gleb on 3/19/2017.
 */
@RestController
public class OAuthController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
