package mx.unam.aragon.zorrito.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // nombre del archivo login.html en tus vistas
    }
}
