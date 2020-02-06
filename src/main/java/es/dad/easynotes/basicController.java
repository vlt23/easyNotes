package es.dad.easynotes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.repository.ApunteRepository;

@Controller
public interface basicController {
	@Autowired
	private ApunteRepository repositorioEjemplar;
	/*
	 @PostConstruct
	 public void init() {
	 repository.save(new Anuncio("Pepe", "Hola..", "XXXX"));
	 repository.save(new Anuncio("Juan", "Adios...", "XXXX"));
	 }
	 @RequestMapping("/")
	 public String tablon(Model model) {
	model.addAttribute("anuncios", repository.findAll());
	return "tablon";
	 }
	 */
}
