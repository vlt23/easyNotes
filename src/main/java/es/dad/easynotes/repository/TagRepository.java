package es.dad.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	Tag findTagByTagIgnoreCase(String tag);

}
