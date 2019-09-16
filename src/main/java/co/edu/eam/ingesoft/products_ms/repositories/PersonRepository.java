package co.edu.eam.ingesoft.products_ms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.eam.ingesoft.products_ms.model.Person;

/**
 * Person Respository.
 * @author caferrerb
 *
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

  /**
   * Find all persons.
   * @return list or persons.
   */
  List<Person> findAll();

  /**
   * Find all persons given a name.
   * @param name name to look for.
   * @return list or persons.
   */
  List<Person> findByName(String name);
}
