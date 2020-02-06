package es.dad.easynotes;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.repository.ApunteRepository;

@Controller
public class basicController {
	@Autowired
	private ApunteRepository repositorioApunte;
	
	 @PostConstruct
	 public void init() {
		 repositorioApunte.save(new Apunte());
		 
	 }
	 
	 /*
	 @RequestMapping("/")
	 public String tablon(Model model) {
	//model.addAttribute("anuncios", repository.findAll());
	return "tablon";
	 }
	 */
	 @GetMapping("/")
		public String greeting(Model model) {

			model.addAttribute("name", "Mundo");

			return "greeting_template";
		}
}
