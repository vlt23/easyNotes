package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Universidad;
import es.dad.easynotes.entity.Usuario;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.AsignaturaRepository;
import es.dad.easynotes.repository.CarreraRepository;
import es.dad.easynotes.repository.UniversidadRepository;
import es.dad.easynotes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SearchController {

    @Autowired
    private ApunteRepository apunteRepo;

    @Autowired
    private AsignaturaRepository asignaturaRepo;

    @Autowired
    private UniversidadRepository universidadRepo;

    @Autowired
    private CarreraRepository carreraRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping("/")
    public String index(Model model) {
    	Usuario usuario;
        // Si hay un usuario logeado, mostramos el usuario
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Si esta registrado se pone el nombre y añade la opcion de logout
        if (!auth.getName().equals("anonymousUser")) {
        	usuario = usuarioRepo.findByNick(auth.getName());
            model.addAttribute("yesLogged", true);

            if (usuario.isAdmin()) {
                model.addAttribute("isAdmin", true);
            }
            model.addAttribute("borrar", true);
            model.addAttribute("nombreUsuario", auth.getName());
            model.addAttribute("creditos", usuario.getCreditos());
        } else {
            model.addAttribute("noLogged", true);
        }
        

        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String buscarAp) {
        Usuario usuario = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            usuario = usuarioRepo.findByNick(auth.getName());
            if (usuario.isAdmin()) {
                model.addAttribute("isAdmin", true);
            }
            // Se pasan los creditos que tiene
            model.addAttribute("creditos", usuario.getCreditos());
        } else {
        	model.addAttribute("creditos", 0);
        }

        List<Apunte> apuntes = apunteRepo.findByTag(buscarAp);

        model.addAttribute("apuntes", apuntes);
        if (apuntes.isEmpty()) {
            model.addAttribute("noResult", true);
        }
        if (usuario == null || (!usuario.isAdmin() && usuario.getCreditos() <= 0)) {
            model.addAttribute("noDownload", true);
        } else {
            model.addAttribute("yesDownload", true);
        }

        return "resultado_busqueda";
    }

    @GetMapping("/searchAsignatura")
    public String searchAsignatura(Model model) {
    	List<Asignatura> asignaturasTemp = asignaturaRepo.findAll();
    	Set<String> asignaturas = new HashSet<>();
    	for (Asignatura asignatura : asignaturasTemp) {
    	    asignaturas.add(asignatura.getNombre());
        }
    	model.addAttribute("asignaturas", asignaturas);
        return "buscar_asignatura";
    }

    @GetMapping("/searchCarrera")
    public String searchCarrera(Model model) {
    	List<Carrera> carrerasTemp = carreraRepo.findAll();
    	Set<String> carreras = new HashSet<>();
    	for (Carrera carrera : carrerasTemp) {
    	    carreras.add(carrera.getNombre());
        }
    	model.addAttribute("carreras", carreras);
        return "buscar_carrera";
    }

    @GetMapping("/searchUniversidad")
    public String searchUniversidad(Model model) {
    	List<Universidad> universidades = universidadRepo.findAll();
    	model.addAttribute("universidades", universidades);
        return "buscar_universidad";
    }

    @PostMapping("/mostrarBusqueda")
    public String mostrarBusqueda(Model model, @RequestParam String tipo, @RequestParam String uniCarreraAsig) {
    	Usuario usuario = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            usuario = usuarioRepo.findByNick(auth.getName());
            model.addAttribute("creditos", usuario.getCreditos());
        } else {
        	model.addAttribute("creditos", 0);
        }

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
    	if (usuario == null || (!usuario.isAdmin() && usuario.getCreditos() <= 0)) {
    	    model.addAttribute("noDownload", true);
        } else {
    	    model.addAttribute("yesDownload", true);
        }

    	return "resultado_busqueda";
    }

}
