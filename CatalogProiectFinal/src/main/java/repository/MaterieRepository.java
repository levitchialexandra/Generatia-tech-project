package repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import basic.Materie;

@Repository
public interface MaterieRepository extends CrudRepository<Materie, Long> {

	Optional<Materie> findById(Long subjectId);
	
	Optional<Materie> findByDenumire(String subjectId);
}
