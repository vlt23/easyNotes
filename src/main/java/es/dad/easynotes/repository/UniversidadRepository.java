package es.dad.easynotes.repository;

import es.dad.easynotes.entity.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversidadRepository extends JpaRepository<Universidad, Long> {

    Universidad findUniversidadByNombreIgnoreCase(String nombre);

}
