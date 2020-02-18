  package es.dad.easynotes.repository;

import java.util.List;

import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Tag;
import es.dad.easynotes.entity.Universidad;
import org.springframework.data.jpa.repository.*;

import es.dad.easynotes.entity.Apunte;

public interface ApunteRepository extends JpaRepository<Apunte, Long> {

	List<Apunte> findByAsignatura(Asignatura asignatura);

	List<Apunte> findByUniversidad(Universidad universidad);

	List<Apunte> findByCarrera(Carrera carrera);
	
	List<Apunte> findByTags(Tag tag);

	@Query(
			value = "SELECT apunte.* FROM TAG JOIN APUNTE_TAGS on (tag.id = apunte_tags.tags_id) JOIN APUNTE on (apunte_tags.apuntes_id = apunte.id) WHERE (tag.tag =?1 )",
			nativeQuery = true)
			List<Apunte> findByTag(String tag);
	

}
