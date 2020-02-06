package es.dad.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.dad.easynotes.entity.Apunte;

public interface ApunteRepository extends JpaRepository<Apunte, Long> {

}
