package mx.unam.aragon.zorrito.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    private  static final Logger logger =
            LoggerFactory.getLogger(indexController.class);

    @GetMapping("/")
    public String home() {
        return "redirect:/login";  // Redirige a login si el usuario accede a /
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
