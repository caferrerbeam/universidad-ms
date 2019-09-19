package co.edu.eam.ingesoft.products_ms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.eam.ingesoft.products_ms.model.Program;

/**
 * Program repository.
 *
 * @author caferrerb
 *
 */
@Repository
public interface ProgramRepository extends CrudRepository<Program, Integer> {

  /**
   * Find all programs.
   *
   * @return list or programs.
   */
  List<Program> findAll();

  /**
   * Find all programs given a name.
   *
   * @param name
   *          name to look for.
   * @return list or programs.
   */
  List<Program> findByName(String name);
}
