package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Usuario;
import es.dad.easynotes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepo;
	
	@RequestMapping("/login")
    public String login(Model model, HttpServletRequest request) {


        return "login";
    }
    @RequestMapping("/loginerror")
    public String loginerror() {
        return "loginerror";
    }


    @RequestMapping("/registro")
    public String registro(Model model) {

        return "registro";
    }

    @PostMapping("/signup")
    public String signup(Model model, @RequestParam String username, @RequestParam String password,
                         @RequestParam String password_repeat, @RequestParam String name,
                         @RequestParam String surname, @RequestParam String email) {
	    if (usuarioRepo.findByNick(username) != null) {
	        model.addAttribute("usernameDup", true);
	        return "loginerror";
        }
	    if (usuarioRepo.findByCorreo(email) != null) {
	        model.addAttribute("emailDup", true);
	        return "loginerror";
        }
	    if (!password.equals(password_repeat)) {
	        model.addAttribute("passwordNotEqual", true);
	        return "loginerror";
        }
        usuarioRepo.save(new Usuario(username, password, name, surname, email));
	    return "login";  // TODO
    }

}
