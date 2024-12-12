package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import basic.Profesor;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor,Long> {

}
