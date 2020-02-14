package es.dad.easynotes.repository;

import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

    Asignatura findAsignaturaByNombreIgnoreCase(String nombre);
    List<Asignatura> findAsignaturaByCarrera_Id(long carrera_id);

}
