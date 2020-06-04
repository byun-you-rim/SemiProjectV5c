package ulim.spring.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import ulim.spring.mvc.service.LoginService;

public class LoginController {

    private LoginService lsrv;


    @RequestMapping(value = "/login/login")
    public String login() {

        return "redirect:/index";
    }

    @RequestMapping(value = "login/logout")
    public String logout() {

        return "redirect:/index";
    }


};