package co.edu.eam.ingesoft.products_ms.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.eam.ingesoft.products_ms.model.Faculty;

/**
 * Faculty Respository.
 * @author cristian.
 *
 */
@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Integer> {

}
