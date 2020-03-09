package es.dad.easynotes.repository;

import java.util.List;

import es.dad.easynotes.entity.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;

import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Tag;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
	
	Carrera findCarreraByNombreIgnoreCase(String nombre);
	List<Carrera> findCarreraByUniversidad_Id(long universidad_id);

	List<Carrera> findCarrerasByNombreIgnoreCase(String nombre);

	Carrera findCarreraByNombreAndUniversidad_Id(String nombre, long uniId);

}	
