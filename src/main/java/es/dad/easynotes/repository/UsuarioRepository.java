package es.dad.easynotes.repository;

import es.dad.easynotes.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	 Usuario findByNick(String name);

}
