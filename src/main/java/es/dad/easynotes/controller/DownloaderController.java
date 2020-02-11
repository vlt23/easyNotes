package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.repository.ApunteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class DownloaderController {

    @Autowired
    private ApunteRepository apunteRepo;

    @RequestMapping("/download/{idApunte}")
    public void downloadResource(HttpServletResponse response, @PathVariable long idApunte) {
        Apunte apunte = apunteRepo.getOne(idApunte);
        try {
            Files.copy(apunte.getFile().toPath(), response.getOutputStream());
            response.getOutputStream().flush();
            apunte.setNumeroDescargas(apunte.getNumeroDescargas() + 1);
            apunteRepo.save(apunte);  // Tras incrementar el numero de descarga hay que guardar el objeto en DB
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
