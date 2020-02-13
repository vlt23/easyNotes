package es.dad.easynotes;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Universidad;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.AsignaturaRepository;
import es.dad.easynotes.repository.CarreraRepository;
import es.dad.easynotes.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class basicController {

    @Autowired
    private ApunteRepository repositorioApunte;

    @Autowired
    private UniversidadRepository universidadRepo;

    @Autowired
    private AsignaturaRepository asignaturaRepo;
    
    @Autowired
    private CarreraRepository carreraRepo;

    @PostConstruct
    public void init() {
    	
    	Carrera GIC = new Carrera("GIC");
    	
    	Carrera GII = new Carrera("GII");
    	
        Universidad URJC = new Universidad("URJC", "Mostoles");
        Universidad UPM = new Universidad("UPM", "Boadilla del Monte");
        
        GIC.setUniversidad(UPM);
        GII.setUniversidad(URJC);
        
        //URJC.getCarreras().add(GII);
        
        universidadRepo.save(URJC);
        universidadRepo.save(UPM);
        
    	carreraRepo.save(GIC);
    	carreraRepo.save(GII);
    	
        Asignatura DAD = new Asignatura("DAD", URJC, GIC, "Patxi");
        asignaturaRepo.save(DAD);
        URJC.getAsignaturas().add(DAD);
        universidadRepo.save(URJC);
        
        Asignatura POO = new Asignatura("POO", URJC, GII, "el de POO");
        asignaturaRepo.save(POO);
        URJC.getAsignaturas().add(POO);
        //universidadRepo.save(URJC);
        
        Asignatura ED = new Asignatura("ED", UPM, GIC, "Buenaposada");
        asignaturaRepo.save(ED);
        UPM.getAsignaturas().add(ED);
        //universidadRepo.save(UPM);
        
        Asignatura logica = new Asignatura("Logica", URJC, GII, "Alexandra");
        asignaturaRepo.save(logica);
        URJC.getAsignaturas().add(logica);
        //universidadRepo.save(URJC);
        
        
        List<Asignatura> GIIasignaturas = new ArrayList<>();
        GIIasignaturas.add(POO);
        GIIasignaturas.add(logica);
        GII.setAsignaturas(GIIasignaturas);
        //carreraRepo.save(GII);
    	
        GIC.getAsignaturas().add(DAD);
        GIC.getAsignaturas().add(ED);
        carreraRepo.save(GIC);

        repositorioApunte.save(new Apunte(DAD, GIC, URJC,
                /*Arrays.asList("Programacion", "Web", "Java")*/new ArrayList<>(), new File("/home/valen/latitude-e5440-laptop_owners-manual_en-us.pdf")));
        repositorioApunte.save(new Apunte(POO, GII, URJC,
                /*Arrays.asList("Programacion", "Java")*/new ArrayList<>(), new File("/home/valen/zswap.sh")));
        repositorioApunte.save(new Apunte(ED, GIC, URJC,
                /*Arrays.asList("Programacion", "C++")*/new ArrayList<>(), new File("/home/valen/zswap.sh")));
        repositorioApunte.save(new Apunte(logica, GII, UPM,
                /*Arrays.asList("Matematicas", "Primer orden")*/new ArrayList<>(), new File("/home/valen/arduinoSN.png")));
        repositorioApunte.save(new Apunte(ED, GII, URJC,
                new ArrayList<>(), new File("/home/valen/zswap.sh")));
    }

}
