package es.dad.easynotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	List<Tag> findByTag(String tag);
	Tag findTagByTagIgnoreCase(String tag);
	

}
