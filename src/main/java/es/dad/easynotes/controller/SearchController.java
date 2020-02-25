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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SearchController {

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
        List<Apunte> apuntes = apunteRepo.findByTag(buscarAp);

        model.addAttribute("apuntes", apuntes);
        if (apuntes.isEmpty()) {
            model.addAttribute("noResult", true);
        }
        return "resultado_busqueda";
    }

    @RequestMapping("/searchAsignatura")
    public String searchAsignatura(Model model) {
    	List<Asignatura> asignaturasTemp = asignaturaRepo.findAll();
    	Set<String> asignaturas = new HashSet<>();
    	for (Asignatura asignatura : asignaturasTemp) {
    	    asignaturas.add(asignatura.getNombre());
        }
    	model.addAttribute("asignaturas", asignaturas);
        return "buscar_asignatura";
    }

    @RequestMapping("/searchCarrera")
    public String searchCarrera(Model model) {
    	List<Carrera> carrerasTemp = carreraRepo.findAll();
    	Set<String> carreras = new HashSet<>();
    	for (Carrera carrera : carrerasTemp) {
    	    carreras.add(carrera.getNombre());
        }
    	model.addAttribute("carreras", carreras);
        return "buscar_carrera";
    }

    @RequestMapping("/searchUniversidad")
    public String searchUniversidad(Model model) {
    	List<Universidad> universidades = universidadRepo.findAll();
    	model.addAttribute("universidades", universidades);
        return "buscar_universidad";
    }

    @RequestMapping("/mostrarBusqueda")
    public String mostrarBusqueda(Model model, @RequestParam String tipo, @RequestParam String uniCarreraAsig) {

    	List<Apunte> apuntes = new ArrayList<>();

    	if (tipo.equals("universidad")) {
    		Universidad universidad = universidadRepo.findUniversidadByNombreIgnoreCase(uniCarreraAsig);
    		apuntes = apunteRepo.findByUniversidad(universidad);
    	} else if (tipo.equals("carrera")) {
    		List<Carrera> carreras = carreraRepo.findCarrerasByNombreIgnoreCase(uniCarreraAsig);
    		for (Carrera carrera : carreras) {
                apuntes.addAll(apunteRepo.findByCarrera(carrera));
            }
    	} else {
    		List<Asignatura> asignaturas = asignaturaRepo.findAsignaturasByNombreIgnoreCase(uniCarreraAsig);
    		for (Asignatura asignatura : asignaturas) {
                apuntes.addAll(apunteRepo.findByAsignatura(asignatura));
            }
    	}
    	model.addAttribute("apuntes", apuntes);

    	return "resultado_busqueda";
    }

}
