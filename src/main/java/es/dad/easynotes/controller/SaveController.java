package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Universidad;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.AsignaturaRepository;
import es.dad.easynotes.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;

@Controller
public class SaveController {

    @Autowired
    private ApunteRepository apunteRepo;

    @Autowired
    private AsignaturaRepository asignaturaRepo;

    @Autowired
    private UniversidadRepository universidadRepo;

    @RequestMapping("/save")
    public String saveApunte(Model model, @RequestParam String asignStr, @RequestParam Carrera carrera,
                             @RequestParam String uniStr, @RequestParam File pdf) {
        Asignatura asignatura = asignaturaRepo.findAsignaturaByNombreIgnoreCase(asignStr);
        Universidad universidad = universidadRepo.findUniversidadByNombre(uniStr);
        if (asignatura == null) {
            asignatura = new Asignatura(asignStr, universidad, carrera, "TODO");
            asignaturaRepo.save(asignatura);
        }
        Apunte apunteSinId = new Apunte(asignatura, carrera, universidad, new ArrayList<>(), pdf);
        Apunte apunte = apunteRepo.save(apunteSinId);
        model.addAttribute("apunte", apunte);
        return "resultado_guardar";
    }
    
    @RequestMapping("/subirApunte")
    public String subirApunte() {
    	return "subir_apunte";
    }
    
    @RequestMapping("/apunteSubido")
    public String apunteSubido(Model model,  @RequestParam String universidad, @RequestParam String asignatura, 
    		@RequestParam String carrera, @RequestParam String tags, @RequestParam String autor) {
    	return "index";
    }

    @RequestMapping("/delete/{idApunte}")
    public String deleteApunte(@PathVariable long idApunte) {
        Apunte apunte = apunteRepo.getOne(idApunte);
        apunteRepo.delete(apunte);
        return "borrar_ok";
    }

}
