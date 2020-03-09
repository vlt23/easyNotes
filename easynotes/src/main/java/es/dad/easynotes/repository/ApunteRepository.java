  package es.dad.easynotes.repository;

  import es.dad.easynotes.entity.Apunte;
  import es.dad.easynotes.entity.Asignatura;
  import es.dad.easynotes.entity.Carrera;
  import es.dad.easynotes.entity.Universidad;
  import org.springframework.data.jpa.repository.JpaRepository;
  import org.springframework.data.jpa.repository.Query;

  import java.util.List;

public interface ApunteRepository extends JpaRepository<Apunte, Long> {

	List<Apunte> findByAsignatura(Asignatura asignatura);

	List<Apunte> findByUniversidad(Universidad universidad);

	List<Apunte> findByCarrera(Carrera carrera);

	@Query(value = "SELECT apunte.* FROM tag JOIN apunte_tags on (tag.id = apunte_tags.tags_id) JOIN apunte on (apunte_tags.apuntes_id = apunte.id) WHERE (tag.tag =?1 )",
			nativeQuery = true)
	List<Apunte> findByTag(String tag);

}
