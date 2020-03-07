package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Tag;
import es.dad.easynotes.entity.Universidad;
import es.dad.easynotes.entity.Usuario;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.AsignaturaRepository;
import es.dad.easynotes.repository.CarreraRepository;
import es.dad.easynotes.repository.UniversidadRepository;
import es.dad.easynotes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class SaveController {

    final String pathLocal = ("src" + File.separator + "main" + File.separator + "resources"
            + File.separator + "files" + File.separator);

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

    @GetMapping("/saveApunte/{uniStr}/{carreraStr}")
    public String saveApunte(Model model,
                             @PathVariable String uniStr, @PathVariable String carreraStr, @RequestParam String asigStr,
                             @RequestParam String tags, @RequestParam MultipartFile file,
                             @RequestParam String nombre, @RequestParam (defaultValue = "false") boolean esExamen) {
    	
        Universidad universidad = universidadRepo.findUniversidadByNombreIgnoreCase(uniStr);
        Carrera carrera = carreraRepo.findCarreraByNombreAndUniversidad_Id(carreraStr, universidad.getId());
        Asignatura asignatura = asignaturaRepo.findAsignaturaByNombreAndCarrera_Id(asigStr, carrera.getId());

        List<Usuario> usuariosTemp = usuarioRepo.findAll();
        Usuario autor = usuariosTemp.get(1); // TODO

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHHmmss"); 	// Poner forma al DATE
        Path filePath = Paths.get(pathLocal,file.hashCode() + "_"
                + formatter.format(LocalDateTime.now()) + "_" + file.getOriginalFilename());
        try {
            OutputStream os = Files.newOutputStream(filePath);
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String [] tag = tags.split(",");
        
        Apunte apunteSinId = new Apunte(nombre, asignatura, carrera, universidad, filePath.toFile(), autor, LocalDateTime.now(), esExamen);
        for(String s : tag) {
        	apunteSinId.getTags().add(new Tag(s.trim()));
        }
        
        Apunte apunte = apunteRepo.save(apunteSinId);
        autor.increaseCreditos();
        usuarioRepo.save(autor);
        model.addAttribute("apunte", apunte);
        return "resultado_guardar";
    }

    @GetMapping("/subirApunte")
    public String subirApunte(Model model) {
        List<Universidad> universidades = universidadRepo.findAll();

        model.addAttribute("universidades", universidades);

        return "subir_universidad";
    }
    
    @GetMapping("/subirCarrera")
    public String subirCarrera(Model model, @RequestParam String uniStr) {
        Universidad universidad = universidadRepo.findUniversidadByNombreIgnoreCase(uniStr);
        long idUni = universidad.getId();
        List<Carrera> carreras = carreraRepo.findCarreraByUniversidad_Id(idUni);

        model.addAttribute("universidad", universidad);
        model.addAttribute("carreras", carreras);

        return "subir_carrera";
    }

    @GetMapping("/subirAsignatura/{uniStr}")
    public String subirAsignatura(Model model, @PathVariable String uniStr, @RequestParam String carreraStr) {

        Universidad universidad = universidadRepo.findUniversidadByNombreIgnoreCase(uniStr);
        Carrera carrera = carreraRepo.findCarreraByNombreAndUniversidad_Id(carreraStr, universidad.getId());
        long idCar = carrera.getId();
        List<Asignatura> asignaturas = asignaturaRepo.findAsignaturaByCarrera_Id(idCar);

        model.addAttribute("universidad", universidad);
        model.addAttribute("carrera", carrera);
        model.addAttribute("asignaturas", asignaturas);

        return "subir_asignatura";
    }

    @GetMapping("/delete/{idApunte}")
    public String deleteApunte(@PathVariable long idApunte) {
        Apunte apunte = apunteRepo.getOne(idApunte);
        File file = new File(pathLocal + apunte.getFilePath().getName());
        apunteRepo.delete(apunte);

        if (file.delete()) {
            return "borrar_ok";
        }
        return "error";
    }
    
    @GetMapping("/anadirUniversidad")
    public String anadirUniversidad() {
        
        return "anadir_universidad";
    }
    
    @GetMapping("/anadirCarrera")
    public String anadirCarrera(Model model) {
    	
    	List<Universidad> universidades = universidadRepo.findAll();
    	
    	model.addAttribute("universidades", universidades);
        
        return "anadir_carrera";
    }
    
    @GetMapping("/anadirAsignatura")
    public String anadirAsignatura(Model model) {
    	
		List<Universidad> universidades = universidadRepo.findAll();
    	
    	model.addAttribute("universidades", universidades);
    	
        return "anadir_asignatura";
    }
    
    @GetMapping("/anadirAsignaturaCarrera")
    public String anadirAsignaturaCarrera(Model model, @RequestParam String uniStr) {
    	Universidad universidad = universidadRepo.findUniversidadByNombreIgnoreCase(uniStr);
    	List<Carrera> carreras = carreraRepo.findCarreraByUniversidad_Id(universidad.getId());
    	
    	model.addAttribute("universidad", universidad);
    	model.addAttribute("carreras", carreras);
    	
        return "anadir_asignatura_carrera";
    }
    
    @GetMapping("/anadida_universidad")
    public String anadirUniversidad(Model model, @RequestParam String uniStr) {
		Universidad comprobar = universidadRepo.findUniversidadByNombreIgnoreCase(uniStr);
		if(comprobar != null) {
			model.addAttribute("error", "error");
		}else {
			Universidad universidad = new Universidad(uniStr);
	    	universidadRepo.save(universidad);
	    	model.addAttribute("exito", "exito");
		}
    	model.addAttribute("nombre", uniStr);
    	model.addAttribute("tipo", "Universidad");
      
        return "anadir";
    }
    
    @GetMapping("/anadida_carrera")
    public String anadirCarrera(Model model, @RequestParam String carreraStr, @RequestParam String uniStr) {
    	Universidad universidad = universidadRepo.findUniversidadByNombreIgnoreCase(uniStr);
		List<Carrera> comprobar = carreraRepo.findCarreraByUniversidad_Id(universidad.getId());
		boolean found = false;
		for (Carrera c : comprobar) {
            if (c.getNombre().equals(carreraStr)) {
                found = true;
                break;
            }
		}
		if (found) {
			model.addAttribute("error", "error");
		} else {
			Carrera carrera = new Carrera(carreraStr, universidad);
	    	carreraRepo.save(carrera);
	    	model.addAttribute("exito", "exito");
		}
    	model.addAttribute("nombre", carreraStr);
    	model.addAttribute("tipo", "Carrera");
      
        return "anadir";
    }
    
    @GetMapping("/anadida_asignatura/{uniStr}")
    public String anadirAsignatura(Model model, @RequestParam String asigStr, @PathVariable String uniStr, @RequestParam String carreraStr, @RequestParam String profesores) {
		Universidad universidad = universidadRepo.findUniversidadByNombreIgnoreCase(uniStr);
		Carrera carrera = carreraRepo.findCarreraByNombreAndUniversidad_Id(carreraStr, universidad.getId());
		List<Asignatura> comprobar = asignaturaRepo.findAsignaturaByCarrera_IdAndUniversidad_Id(carrera.getId(), universidad.getId());
		boolean found = false;
		for (Asignatura a : comprobar) {
            if (a.getNombre().equals(asigStr)) {
                found = true;
                break;
            }
		}
		
		if (found) {
			model.addAttribute("error", "error");
		} else {
			Asignatura asignatura = new Asignatura(asigStr, universidad, carrera, profesores);
	    	asignaturaRepo.save(asignatura);
	    	model.addAttribute("exito", "exito");
		}
    	model.addAttribute("nombre", asigStr);
    	model.addAttribute("tipo", "Asignatura");
      
        return "anadir";
    }

}
