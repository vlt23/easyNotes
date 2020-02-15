package es.dad.easynotes;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Universidad;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.AsignaturaRepository;
import es.dad.easynotes.repository.CarreraRepository;
import es.dad.easynotes.repository.TagRepository;
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
    
    @Autowired
    private TagRepository tagRepo;

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

        Apunte manualPortatil=new Apunte("manualPortatil", DAD, GIC, URJC,new File("/home/valen/latitude-e5440-laptop_owners-manual_en-us.pdf"));
        repositorioApunte.save(manualPortatil);
        
        Apunte script1 = new Apunte("script", POO, GII, URJC, new File("/home/valen/zswap.sh"));
        repositorioApunte.save(script1);
        Apunte script2 = new Apunte("script", ED, GIC, URJC, new File("/home/valen/zswap.sh"));
        repositorioApunte.save(script2);
        Apunte arduino = new Apunte("arduino", logica, GII, UPM, new File("/home/valen/arduinoSN.png"));
        repositorioApunte.save(arduino);
        Apunte script3 = new Apunte("script", ED, GII, URJC, new File("/home/valen/arduinoSN.png"));
        repositorioApunte.save(script3);
        Apunte arduino2 = new Apunte("arduino", POO, GII, URJC, new File("/home/valen/arduinoSN.png"));
        repositorioApunte.save(arduino2);
        
        
    }

}
