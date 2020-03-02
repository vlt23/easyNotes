package es.dad.easynotes.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.dad.easynotes.entity.Universidad;

@Controller
public class LoginController {
	
	
	@RequestMapping("/login")
    public String login(Model model) {

        return "login";
    }
	
	@RequestMapping("/registro")
    public String registro(Model model) {

        return "registro";
    }
	

}
