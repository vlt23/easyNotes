package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Email;
import es.dad.easynotes.entity.Usuario;
import es.dad.easynotes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping("/login")
    public String login(Model model) {
        return "login_template";
    }

    @GetMapping("/loginerror")
    public String loginerror(Model model) {
    	model.addAttribute("error", true);
        return "loginerror";
    }

    @GetMapping("/registro")
    public String registro(Model model) {

        return "registro";
    }

    @Value("${valor.mail.ip}")
    private String hostIp;
    @PostMapping("/signup")
    public String signup(Model model, @RequestParam String username, @RequestParam String password,
                         @RequestParam String password_repeat, @RequestParam String name,
                         @RequestParam String surname, @RequestParam String email) {
	    if (usuarioRepo.findByNick(username) != null) {
	        model.addAttribute("usernameDup", true);
	        return "loginerror";
        }
	    if (!password.equals(password_repeat)) {
	    	model.addAttribute("passwordsNotEquals", true);
	        return "loginerror";
	    }
	    if (usuarioRepo.findByCorreo(email) != null) {
	        model.addAttribute("emailDup", true);
	        return "loginerror";
        }

        usuarioRepo.save(new Usuario(username, password, name, surname, email, false));

        Email welcomeEmail = new Email(username, email, Email.Topic.WELCOME);
        Thread emailThread = new Thread(() ->
                WebClient.create().post().uri(URI.create(hostIp+":8025/email"))
                .body(BodyInserters.fromValue(welcomeEmail)).exchange().block());
        emailThread.start();

	    return "login_template";
    }

}
