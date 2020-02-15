package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Tag;
import es.dad.easynotes.entity.Universidad;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.AsignaturaRepository;
import es.dad.easynotes.repository.CarreraRepository;
import es.dad.easynotes.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SaveController {

    @Autowired
    private ApunteRepository apunteRepo;

    @Autowired
    private AsignaturaRepository asignaturaRepo;

    @Autowired
    private UniversidadRepository universidadRepo;
    
    @Autowired
    private CarreraRepository carreraRepo;

    @PostMapping("/saveApunte/{uniStr}/{carreraStr}")
    public String saveApunte(Model model,
    		@PathVariable String uniStr, @PathVariable String carreraStr, @RequestParam String asigStr, @RequestParam String autorStr,
                             @RequestParam List<Tag> tags, @RequestParam MultipartFile file, @RequestParam String nombre) {
    	
        Asignatura asignatura = asignaturaRepo.findAsignaturaByNombreIgnoreCase(asigStr);
        Universidad universidad = universidadRepo.findUniversidadByNombreIgnoreCase(uniStr);
        Carrera carrera = carreraRepo.findCarreraByNombreIgnoreCase(carreraStr);
        
        //NO necesario al crearse en Apuntes
        //tags.add(new Tag(asignatura.getNombre()));
        //tags.add(new Tag(universidad.getNombre()));
        //tags.add(new Tag(carrera.getNombre()));
        
        //EL USUARIO NO DEBERÍA PODER CREAR ASIGNATURAS, SOLO ELEGIR ENTRE LAS QUE YA HAY
        /*if (asignatura == null) {
            asignatura = new Asignatura(asignStr, universidad, carrera, "TODO");
            asignaturaRepo.save(asignatura);
        }*/

        // TODO: don't hardcode the path
        //HAY que añadirle el nombre de fichero desde el form
        Path filePath = Paths.get("/home/valen/Universidad/4curso/2cuatri/DAD/easyNotes/src/main/resources/files",
                file.hashCode() + "_" + LocalDateTime.now().toString() + "_" + file.getOriginalFilename());
        try {
            OutputStream os = Files.newOutputStream(filePath);
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TEMPORAL
        Apunte apunteSinId = new Apunte(nombre, asignatura, carrera, universidad, filePath.toFile());
        apunteSinId.getTags().addAll(tags);	//NOSESIFUNCIONA
        
        Apunte apunte = apunteRepo.save(apunteSinId);
        model.addAttribute("apunte", apunte);
        return "resultado_guardar";
    }
    
    @RequestMapping("/subirApunte")
    public String subirApunte(Model model) {
    	
    	List<Universidad> universidades = universidadRepo.findAll();
    	//List<Asignatura> asignaturas = asignaturaRepo.findAll();
    	//List<Carrera> carreras = carreraRepo.findAll();
    	
    	model.addAttribute("universidades", universidades);
    	//model.addAttribute("asignaturas", asignaturas);
    	//model.addAttribute("carreras", carreras);
    	
    	return "subir_universidad";
    }
    
    @RequestMapping("/subirCarrera")
    public String subirCarrera(Model model, @RequestParam String uniStr) {
    	
    	Universidad universidad = universidadRepo.findUniversidadByNombreIgnoreCase(uniStr);
    	long idUni = universidad.getId();
    	List<Carrera> carreras = carreraRepo.findCarreraByUniversidad_Id(idUni);	
    	//List<Asignatura> asignaturas = asignaturaRepo.findAll();
    	//List<Carrera> carreras = carreraRepo.findAll();
    	
    	model.addAttribute("universidad", universidad);
    	model.addAttribute("carreras", carreras);
    	//model.addAttribute("asignaturas", asignaturas);
    	//model.addAttribute("carreras", carreras);
    	
    	return "subir_carrera";
    }
    
    @RequestMapping("/subirAsignatura/{uniStr}")
    public String subirAsignatura(Model model, @PathVariable String uniStr, @RequestParam String carreraStr) {
    	
    	Universidad universidad = universidadRepo.findUniversidadByNombreIgnoreCase(uniStr);
    	Carrera carrera = carreraRepo.findCarreraByNombreIgnoreCase(carreraStr);
    	long idCar = carrera.getId();
    	List<Asignatura> asignaturas = asignaturaRepo.findAsignaturaByCarrera_Id(idCar);	
    	//List<Asignatura> asignaturas = asignaturaRepo.findAll();
    	//List<Carrera> carreras = carreraRepo.findAll();
    	
    	model.addAttribute("universidad", universidad);
    	model.addAttribute("carrera", carrera);
    	model.addAttribute("asignaturas", asignaturas);
    	//model.addAttribute("asignaturas", asignaturas);
    	//model.addAttribute("carreras", carreras);
    	
    	return "subir_asignatura";
    }

    @RequestMapping("/delete/{idApunte}")
    public String deleteApunte(@PathVariable long idApunte) {
        Apunte apunte = apunteRepo.getOne(idApunte);
        apunteRepo.delete(apunte);

        // TODO: don't hardcode the path
        File file = new File("/home/valen/Universidad/4curso/2cuatri/DAD/easyNotes/src/main/resources/files/" +
                apunte.getNombre());
        if (file.delete()) {
            return "borrar_ok";
        }
        return "error";
    }

}
