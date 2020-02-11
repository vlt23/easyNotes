package es.dad.easynotes;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Universidad;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.AsignaturaRepository;
import es.dad.easynotes.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;

@Controller
public class basicController {

    @Autowired
    private ApunteRepository repositorioApunte;

    @Autowired
    private UniversidadRepository universidadRepo;

    @Autowired
    private AsignaturaRepository asignaturaRepo;

    @PostConstruct
    public void init() {
        Universidad URJC = new Universidad("URJC", "Mostoles");
        universidadRepo.save(URJC);
        Universidad UPM = new Universidad("UPM", "Boadilla del Monte");
        universidadRepo.save(UPM);
        Asignatura DAD = new Asignatura("DAD", URJC, "GIC", "Patxi");
        asignaturaRepo.save(DAD);
        Asignatura POO = new Asignatura("POO", URJC, "GII", "el de POO");
        asignaturaRepo.save(POO);
        Asignatura ED = new Asignatura("ED", URJC, "GIC", "Buenaposada");
        asignaturaRepo.save(ED);
        Asignatura logica = new Asignatura("Logica", URJC, "GII", "Alexandra");
        asignaturaRepo.save(logica);

        repositorioApunte.save(new Apunte(DAD, "GIC", URJC,
                /*Arrays.asList("Programacion", "Web", "Java")*/new ArrayList<>(), new File("/home/valen/latitude-e5440-laptop_owners-manual_en-us.pdf")));
        repositorioApunte.save(new Apunte(POO, "GII", URJC,
                /*Arrays.asList("Programacion", "Java")*/new ArrayList<>(), new File("/home/valen/zswap.sh")));
        repositorioApunte.save(new Apunte(ED, "GIC", URJC,
                /*Arrays.asList("Programacion", "C++")*/new ArrayList<>(), new File("/home/valen/zswap.sh")));
        repositorioApunte.save(new Apunte(logica, "GII", UPM,
                /*Arrays.asList("Matematicas", "Primer orden")*/new ArrayList<>(), new File("/home/valen/arduinoSN.png")));
        repositorioApunte.save(new Apunte(ED, "GII", URJC,
                new ArrayList<>(), new File("/home/valen/zswap.sh")));
    }

}
