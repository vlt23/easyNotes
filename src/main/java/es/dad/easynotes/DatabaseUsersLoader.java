package es.dad.easynotes;

import javax.annotation.PostConstruct;

import es.dad.easynotes.entity.Usuario;
import es.dad.easynotes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private UsuarioRepository userRepository;

    @PostConstruct
    private void initDatabase() {
    	
    	userRepository.save(new Usuario("user", "1234", "ROLE_USER"));
		userRepository.save(new Usuario("admin", "1234", "ROLE_USER", "ROLE_ADMIN"));
    }

}
