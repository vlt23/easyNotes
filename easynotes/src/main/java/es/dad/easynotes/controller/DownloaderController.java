package es.dad.easynotes.controller;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Usuario;
import es.dad.easynotes.repository.ApunteRepository;
import es.dad.easynotes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;

@Controller
public class DownloaderController {

    @Autowired
    private ApunteRepository apunteRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping("/show/{idApunte}")
    public void showResource(HttpServletResponse response, @PathVariable long idApunte) throws IOException {
        Apunte apunte = apunteRepo.getOne(idApunte);
        response.setContentType("application/pdf");
        InputStream inputStream = new FileInputStream(new File(apunte.getFilePath().getPath()));

        int nRead;
        while ((nRead = inputStream.read()) != -1) {
            response.getWriter().write(nRead);
        }

    }

    @GetMapping("/download/{idApunte}")
    public String downloadResource(HttpServletResponse response, @PathVariable long idApunte) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = usuarioRepo.findByNick(auth.getName());


        Apunte apunte = apunteRepo.getOne(idApunte);
        try {
            Files.copy(apunte.getFilePath().toPath(), response.getOutputStream());
            response.getOutputStream().flush();
            if (!user.isAdmin())
            	user.decreaseCreditos();
            user.increaseNumeroDescargas();
            usuarioRepo.save(user);
            apunte.setNumeroDescargas(apunte.getNumeroDescargas() + 1);
            apunteRepo.save(apunte);  // Tras incrementar el numero de descarga hay que guardar el objeto en DB
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "resultado_busqueda";
    }

}
