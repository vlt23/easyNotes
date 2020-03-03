package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Usuario;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.UsuarioRepository;
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

    @Autowired
    private UsuarioRepository usuarioRepo;

    @RequestMapping("/download/{idApunte}")
    public void downloadResource(HttpServletResponse response, @PathVariable long idApunte) {
        Usuario usuario = usuarioRepo.findAll().get(1);  // TODO

        // TODO
        if (usuario.getCreditos() <= 0) {
            return;
        }

        Apunte apunte = apunteRepo.getOne(idApunte);
        Usuario autor = apunte.getAutor();
        if (!usuario.equals(autor)) {
            autor.increaseNumeroDescargas();
            usuarioRepo.save(autor);
            usuario.decreaseCreditos();
            usuarioRepo.save(usuario);
        }

        try {
            Files.copy(apunte.getFilePath().toPath(), response.getOutputStream());
            response.getOutputStream().flush();
            apunte.setNumeroDescargas(apunte.getNumeroDescargas() + 1);
            apunteRepo.save(apunte);  // Tras incrementar el numero de descarga hay que guardar el objeto en DB
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
