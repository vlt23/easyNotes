package es.dad.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.dad.easynotes.entity.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
	Carrera findCarreraByNameIgnoreCase(String name);
}
