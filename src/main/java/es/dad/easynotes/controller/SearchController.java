package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Tag;
import es.dad.easynotes.entity.Universidad;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.AsignaturaRepository;
import es.dad.easynotes.repository.CarreraRepository;
import es.dad.easynotes.repository.TagRepository;
import es.dad.easynotes.repository.UniversidadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
	final String pathLocal = ("src"+File.separator+"main"+File.separator+"resources"+File.separator+ "files"+ File.separator);

    @Autowired
    private ApunteRepository apunteRepo;
    
    @Autowired
    private TagRepository tagRepo;

    @Autowired
    private AsignaturaRepository asignaturaRepo;
    
    @Autowired
    private UniversidadRepository universidadRepo;
    
    @Autowired
    private CarreraRepository carreraRepo;

    @RequestMapping("/search")
    public String search(Model model, @RequestParam String buscarAp) {
        //Asignatura asignatura = asignaturaRepo.findAsignaturaByNombreIgnoreCase(buscarAp);
    	Tag tag = new Tag(buscarAp);
        List<Apunte> apuntes = apunteRepo.findByTags(tag);
        model.addAttribute("apunte", apuntes.get(0));  // TODO
        return "resultado_guardar";
    }
    
    @RequestMapping("/searchAsignatura")
    public String searchAsignatura(Model model) {
    	List<Asignatura> asignaturas = asignaturaRepo.findAll();
    	model.addAttribute("asignaturas", asignaturas);
        return "buscar_asignatura";
    }

   
    @RequestMapping("/searchCarrera")
    public String searchCarrera(Model model) {
    	List<Carrera> carreras = carreraRepo.findAll();
    	model.addAttribute("carreras", carreras);
        return "buscar_carrera";
    }
    
    @RequestMapping("/searchUniversidad")
    public String searchUniversidad(Model model) {
    	List<Universidad> universidades = universidadRepo.findAll();
    	model.addAttribute("universidades", universidades);
        return "buscar_universidad";
    }

    @RequestMapping("/advancedSearch")
    public String advancedSearch(Model model) {
        return null;
    }

}
