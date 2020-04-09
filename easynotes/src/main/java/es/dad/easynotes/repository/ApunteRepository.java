package es.dad.easynotes.repository;

import es.dad.easynotes.entity.Apunte;
import es.dad.easynotes.entity.Asignatura;
import es.dad.easynotes.entity.Carrera;
import es.dad.easynotes.entity.Universidad;
import es.dad.easynotes.entity.Usuario;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@CacheConfig(cacheNames = "apuntes")
public interface ApunteRepository extends JpaRepository<Apunte, Long> {

	@Cacheable
	List<Apunte> findByAsignatura(Asignatura asignatura);

	@Cacheable
	List<Apunte> findByUniversidad(Universidad universidad);

	@Cacheable
	List<Apunte> findByCarrera(Carrera carrera);

	@Cacheable
	List<Apunte> findByAutor(Usuario autor);

	@Cacheable
	@Query(value = "SELECT apunte.* FROM tag JOIN apunte_tags on (UPPER(tag.id) = UPPER(apunte_tags.tags_id)) JOIN apunte on (UPPER(apunte_tags.apuntes_id) = UPPER(apunte.id)) WHERE (UPPER(tag.tag) = UPPER(?1) )",
			nativeQuery = true)
	List<Apunte> findByTag(String tag);

	@CacheEvict(allEntries = true)
	@Override
	<S extends Apunte> S save(S s);

	@CacheEvict(allEntries = true)
	@Override
	void delete(Apunte apunte);
}
