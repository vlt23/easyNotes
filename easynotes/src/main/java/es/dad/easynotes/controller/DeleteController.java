package es.dad.easynotes.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Usuario;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.UsuarioRepository;

@Controller
public class DeleteController {

	@Autowired
    private ApunteRepository apunteRepo;
	
	@Autowired
    private UsuarioRepository usuarioRepo;
	
	@GetMapping("/delete/{idApunte}")
    public String deleteApunte(@PathVariable long idApunte) {
        Apunte apunte = apunteRepo.getOne(idApunte);
        File file = new File("Files/" + apunte.getFilePath().getName());
        apunteRepo.delete(apunte);

        if (file.delete()) {
            return "borrar_ok";
        }
        return "error";
    }
    
    @PostMapping("/borrarApuntes")
    public String borrarApuntes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepo.findByNick(auth.getName());
        
        List<Apunte> apuntes;
        
        if (usuario.isAdmin()) {
        	apuntes = apunteRepo.findAll();
        } else {
        	apuntes = apunteRepo.findByAutor(usuario);
        }
        
        if (apuntes.isEmpty()) {
        	model.addAttribute("noApuntes", true);
        	model.addAttribute("user", auth.getName());
        } else {
        	model.addAttribute("apuntes", apuntes);
        }
    	
        return "borrar_apuntes";
    }
}
