package es.dad.easynotes;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Universidad;
import es.dad.easynotes.entity.Usuario;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.AsignaturaRepository;
import es.dad.easynotes.repository.CarreraRepository;
import es.dad.easynotes.repository.TagRepository;
import es.dad.easynotes.repository.UniversidadRepository;
import es.dad.easynotes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class basicController {
    private final String pathLocal = ("src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "files" + File.separator);

    @Autowired
    private ApunteRepository repositorioApunte;

    @Autowired
    private UniversidadRepository universidadRepo;

    @Autowired
    private AsignaturaRepository asignaturaRepo;
    
    @Autowired
    private CarreraRepository carreraRepo;
    
    @Autowired
    private TagRepository tagRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostConstruct
    public void init() {
    	
    	Carrera GIC = new Carrera("GIC");
    	
    	Carrera GII = new Carrera("GII");
    	
        Universidad URJC = new Universidad("URJC");
        Universidad UPM = new Universidad("UPM");
        universidadRepo.save(URJC);
        universidadRepo.save(UPM);
        
        GIC.setUniversidad(UPM);
        GII.setUniversidad(URJC);
        
        //URJC.getCarreras().add(GII);

    	carreraRepo.save(GIC);
    	carreraRepo.save(GII);
    	
        Asignatura DAD = new Asignatura("DAD", URJC, GIC, "Patxi");
        DAD = asignaturaRepo.save(DAD);
        URJC.getAsignaturas().add(DAD);
        universidadRepo.save(URJC);
        
        Asignatura POO = new Asignatura("POO", URJC, GII, "el de POO");
        POO = asignaturaRepo.save(POO);
        URJC.getAsignaturas().add(POO);
        //universidadRepo.save(URJC);
        
        Asignatura ED = new Asignatura("ED", UPM, GIC, "Buenaposada");
        ED = asignaturaRepo.save(ED);
        UPM.getAsignaturas().add(ED);
        //universidadRepo.save(UPM);
        
        Asignatura logica = new Asignatura("Logica", URJC, GII, "Alexandra");
        logica = asignaturaRepo.save(logica);
        URJC.getAsignaturas().add(logica);
        //universidadRepo.save(URJC);

        Asignatura SO = new Asignatura("SO", UPM, GIC, "Alberto");
        SO = asignaturaRepo.save(SO);
        UPM.getAsignaturas().add(SO);
        
        List<Asignatura> GIIasignaturas = new ArrayList<>();
        GIIasignaturas.add(POO);
        GIIasignaturas.add(logica);
        GII.setAsignaturas(GIIasignaturas);
        //carreraRepo.save(GII);
    	
        GIC.getAsignaturas().add(DAD);
        GIC.getAsignaturas().add(ED);
        carreraRepo.save(GIC);

        Usuario usuario = new Usuario("admin", "root", 0, "admin@root.org", true);
        Usuario usuarioAdmin = usuarioRepo.save(usuario);

        Apunte manualPortatil = new Apunte("manualPortatil", DAD, GIC, URJC, new File(pathLocal + "manualPortatil.pdf"), usuarioAdmin);
        repositorioApunte.save(manualPortatil);

        Apunte script1 = new Apunte("script", SO, GII, URJC, new File(pathLocal + "zswap.sh"), usuarioAdmin);
        repositorioApunte.save(script1);

        Apunte script2 = new Apunte("script", ED, GIC, URJC, new File(pathLocal + "zram.txt"), usuarioAdmin);
        repositorioApunte.save(script2);

        Apunte script3 = new Apunte("arduino", logica, GII, UPM, new File(pathLocal + "bateria.sh"), usuarioAdmin);
        repositorioApunte.save(script3);

        Apunte cpp = new Apunte("script", ED, GII, URJC, new File(pathLocal + "prueba_linkedlist.cpp"), usuarioAdmin);
        repositorioApunte.save(cpp);

        Apunte arduino2 = new Apunte("arduino", POO, GII, URJC, new File(pathLocal + "arduino.png"), usuarioAdmin);
        repositorioApunte.save(arduino2);

        Apunte esqui = new Apunte("esqui", DAD, GIC, URJC, new File(pathLocal + "pruebaPablo.pdf"), usuarioAdmin);
        repositorioApunte.save(esqui);
    }

}
