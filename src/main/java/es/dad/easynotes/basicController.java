package es.dad.easynotes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.repository.ApunteRepository;

@Controller
public interface basicController {
	

	@Autowired
	 private ApunteRepository repository;
	
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
}
