package es.dad.easynotes.repository;

import es.dad.easynotes.entity.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

    Asignatura findAsignaturaByNombreIgnoreCase(String nombre);

}
