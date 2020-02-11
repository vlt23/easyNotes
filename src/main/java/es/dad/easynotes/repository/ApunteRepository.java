package es.dad.easynotes.repository;

import java.util.List;

import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Universidad;
import org.springframework.data.jpa.repository.*;

import es.dad.easynotes.entity.Apunte;

public interface ApunteRepository extends JpaRepository<Apunte, Long> {

	List<Apunte> findByAsignatura(Asignatura asignatura);

	List<Apunte> findByUniversidad(Universidad universidad);

	List<Apunte> findByCarrera(String carrera);

}
