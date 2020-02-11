package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ApunteRepository apunteRepo;

    @Autowired
    private AsignaturaRepository asignaturaRepo;

    @RequestMapping("/search")
    public String search(Model model, @RequestParam String asignStr) {
        Asignatura asignatura = asignaturaRepo.findAsignaturaByNombreIgnoreCase(asignStr);
        List<Apunte> apuntes = apunteRepo.findByAsignatura(asignatura);
        model.addAttribute("apunte", apuntes.get(0));  // TODO
        return "resultado_guardar";
    }

    @RequestMapping("/advancedSearch")
    public String advancedSearch(Model model) {
        return null;
    }

}
