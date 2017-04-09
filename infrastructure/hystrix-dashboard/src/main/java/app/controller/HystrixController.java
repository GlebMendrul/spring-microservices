package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gleb on 3/19/2017.
 */
@Controller
public class HystrixController {
    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix";
    }
}
