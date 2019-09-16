package co.edu.eam.ingesoft.products_ms.services;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eam.ingesoft.products_ms.model.Person;
import co.edu.eam.ingesoft.products_ms.repositories.PersonRepository;

/**
 * Service to perform bussines operations over Person entity.
 *
 * @author caferrerb
 *
 */
@Service
public class PersonService {

  /**
   * PErson repository.
   */
  @Autowired
  private PersonRepository personRespository;

  /**
   * Craete a personToCreate.
   *
   * @param personToCreate person to create.
   * @return person created
   */
  public Person create(Person personToCreate) {
    Person person = find(personToCreate.getId());

    if (person != null) {
      throw new EntityExistsException("person already exists");
    }

    return personRespository.save(personToCreate);
  }

  /**
   * Update a person.
   *
   * @param person person to update.
   * @return person edited
   */
  public Person update(Person person) {
    Person personToUpdate = find(person.getId());

    if (personToUpdate == null) {
      throw new EntityNotFoundException("person not exists");
    }

    return personRespository.save(person);
  }

  /**
   * Find a person.
   *
   * @param id id to ind the person
   * @return the person found
   */
  public Person find(Integer id) {
    return personRespository.findById(id).orElse(null);
  }

  /**
   * Delete a person.
   *
   * @param id id to delete
   * @return person deleted
   */
  public Person delete(Integer id) {
    Person person = find(id);

    if (person == null) {
      throw new EntityNotFoundException("person not exists");
    }

    personRespository.deleteById(id);

    return person;
  }

  /**
   * List all persons.
   *
   * @return list of all persons
   */
  public List<Person> listAll() {
    return personRespository.findAll();
  }

  /**
   * List persons by name.
   *
   * @param name name to looking for
   * @return list fo person with a name.
   */
  public List<Person> findByName(String name) {
    return personRespository.findByName(name);
  }

}
