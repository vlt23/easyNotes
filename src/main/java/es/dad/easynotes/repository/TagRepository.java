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
	
	
	//@Query(
	//		 value = "SELECT distinct apunte FROM TAG JOIN APUNTE_TAGS on (tag.id = apunte_tags.tags_id) JOIN APUNTE on (apunte_tags.apuntes_id = apunte.id) WHERE (tag.tag =?1 )",
	//		 nativeQuery = true)
	//		List<Apunte> findByTag2(String tag);;

	//List<Apunte> findByTag2

}
