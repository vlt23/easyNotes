package es.dad.easynotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;

import es.dad.easynotes.entity.Apunte;

public interface ApunteRepository extends JpaRepository<Apunte, Long> {

	List<Apunte> findByasignatura(String asignatura);
}
