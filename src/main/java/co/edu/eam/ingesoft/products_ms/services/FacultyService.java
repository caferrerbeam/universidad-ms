package co.edu.eam.ingesoft.products_ms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eam.ingesoft.products_ms.model.Faculty;
import co.edu.eam.ingesoft.products_ms.repositories.FacultyRepository;

import javax.persistence.EntityExistsException;

/**
 * Service to perform bussines operations over Faculty entity.
 *
 * @author cristian.
 *
 */
@Service
public class FacultyService {
  /**
   * program repository.
   */
  @Autowired
  private FacultyRepository facultyRepository;

  /**
   * Craete a facultyToCreate.
   *
   * @param facultyToCreate faculty to create.
   * @return faculty created
   */
  public Faculty create(Faculty facultyToCreate) {
    Faculty faculty = find(facultyToCreate.getId());

    if (faculty != null) {
      throw new EntityExistsException("faculty already exists");
    }

    return facultyRepository.save(facultyToCreate);
  }

  /**
   * Find a faculty.
   * @param id to end the program.
   * @return the program found.
   */
  public Faculty find(Integer id) {
    return facultyRepository.findById(id).orElse(null);
  }
}
