package co.edu.eam.ingesoft.products_ms.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.eam.ingesoft.products_ms.model.Person;
import co.edu.eam.ingesoft.products_ms.routes.Router;
import co.edu.eam.ingesoft.products_ms.services.PersonService;

/**
 * REst controller for person entity.
 *
 * @author caferrerb
 *
 */
@RestController
@RequestMapping(Router.PERSON_PATH)
public class PersonController {

  /**
   * person service.
   */
  @Autowired
  private PersonService personService;

  /**
   * create a person operation.
   *
   * @param person person to create
   * @return person created
   */
  @PostMapping(value = Router.CREATE_PERSON)
  public Person create(@RequestBody Person person) {
    return personService.create(person);
  }

  /**
   * find a person.
   *
   * @param id id for person to find
   * @param response httpresponse
   * @return person with id
   */
  @GetMapping(value = Router.FIND_PERSON + "/{id}")
  public Person find(@PathVariable Integer id, HttpServletResponse response) {
    Person person = personService.find(id);

    if (person == null) {
      response.setStatus(HttpStatus.NOT_FOUND.value());
    }

    return person;
  }

  /**
   * Delete a person.
   *
   * @param id id person to delete
   * @return person deleted
   */
  @DeleteMapping(value = Router.DELETE_PERSON + "/{id}")
  public Person delete(@PathVariable Integer id) {
    return personService.delete(id);
  }

  /**
   * Edit a person.
   *
   * @param person person to edit
   * @return person edited
   */
  @PutMapping(value = Router.EDIT_PERSON)
  public Person edit(@RequestBody Person person) {
    return personService.update(person);
  }

  /**
   * find a person by name.
   *
   * @param name name person to find
   * @return list of person with a name
   */
  @GetMapping(value = Router.FIND_BY_NAME)
  public ResponseEntity<List<Person>> findByName(@RequestParam String name) {
    List<Person> persons = personService.findByName(name);

    if (persons.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(persons, HttpStatus.OK);
  }

  /**
   * list all persons.
   *
   * @param response httpresponse
   * @return list of all persons
   */
  @GetMapping(value = Router.FIND_ALL)
  public List<Person> findAll(HttpServletResponse response) {
    List<Person> persons = personService.listAll();

    if (persons.isEmpty()) {
      response.setStatus(HttpStatus.NO_CONTENT.value());
    }

    return persons;
  }
}
