package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import basic.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

	List<Student> findByClasa(String clasa);
	List<Student> findByNumeContainingIgnoreCaseOrPrenumeContainingIgnoreCase(String nume, String prenume);
	List<Student> findByNumeContainingIgnoreCaseOrPrenumeContainingIgnoreCaseAndClasa(String nume, String prenume, String clasa);
	List<Student> findByNumeContainingOrPrenume(String nume, String prenume);
	Student getStudentById(Long id);
	

}
