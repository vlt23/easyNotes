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

    @RequestMapping("/save")
    public String saveApunte(Model model,
                             @RequestParam String asignStr, @RequestParam String carreraStr,
                             @RequestParam String uniStr, /*@RequestParam String autorStr,*/
                             @RequestParam List<Tag> tags, @RequestParam MultipartFile file) {
    	
        Asignatura asignatura = asignaturaRepo.findAsignaturaByNombreIgnoreCase(asignStr);
        Universidad universidad = universidadRepo.findUniversidadByNombre(uniStr);
        Carrera carrera = carreraRepo.findCarreraByNombreIgnoreCase(carreraStr);
        tags.add(new Tag(asignatura.getNombre()));
        tags.add(new Tag(universidad.getNombre()));
        tags.add(new Tag(carrera.getNombre()));
        
        //EL USUARIO NO DEBERÍA PODER CREAR ASIGNATURAS, SOLO ELEGIR ENTRE LAS QUE YA HAY
        /*if (asignatura == null) {
            asignatura = new Asignatura(asignStr, universidad, carrera, "TODO");
            asignaturaRepo.save(asignatura);
        }*/

        // TODO: don't hardcode the path
        Path filePath = Paths.get("/home/valen/Universidad/4curso/2cuatri/DAD/easyNotes/src/main/resources/files",
                file.getOriginalFilename() + "_" + file.hashCode() + "_" + LocalDateTime.now().toString());
        try {
            OutputStream os = Files.newOutputStream(filePath);
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Apunte apunteSinId = new Apunte(asignatura, carrera, universidad, tags, filePath.toFile());
        Apunte apunte = apunteRepo.save(apunteSinId);
        model.addAttribute("apunte", apunte);
        return "resultado_guardar";
    }
    
    @RequestMapping("/subirApunte")
    public String subirApunte(Model model) {
    	
    	List<Universidad> universidades = universidadRepo.findAll();
    	List<Asignatura> asignaturas = asignaturaRepo.findAll();
    	List<Carrera> carreras = carreraRepo.findAll();
    	
    	model.addAttribute("universidades", universidades);
    	model.addAttribute("asignaturas", asignaturas);
    	model.addAttribute("carreras", carreras);
    	
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

        // TODO: don't hardcode the path
        File file = new File("/home/valen/Universidad/4curso/2cuatri/DAD/easyNotes/src/main/resources/files/" +
                apunte.getNombre());
        if (file.delete()) {
            return "borrar_ok";
        }
        return "error";
    }

}
