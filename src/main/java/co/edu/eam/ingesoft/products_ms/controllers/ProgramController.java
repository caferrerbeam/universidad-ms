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

import co.edu.eam.ingesoft.products_ms.model.Program;
import co.edu.eam.ingesoft.products_ms.routes.Router;
import co.edu.eam.ingesoft.products_ms.services.ProgramService;

/**
 * REst controller for program entity.
 *
 * @author JHON
 *
 */
@RestController
@RequestMapping(Router.PROGRAMS_PATH)
public class ProgramController {

  /**
   * program service.
   */
  @Autowired
  private ProgramService programService;

  /**
   * create a program operation.
   *
   * @param program
   *          program to create
   * @return program created
   */
  @PostMapping(value = Router.CREATE_PROGRAMS)
  public Program create(@RequestBody Program program) {
    return programService.create(program);
  }

  /**
   * find a program.
   *
   * @param id
   *          id for program to find
   * @param response
   *          httpResponse
   * @return program with id
   */
  @GetMapping(value = Router.FIND_PROGRAMS + "/{id}")
  public Program find(@PathVariable Integer id, HttpServletResponse response) {
    Program program = programService.find(id);

    if (program == null) {
      response.setStatus(HttpStatus.NOT_FOUND.value());
    }

    return program;
  }

  /**
   * Delete a program.
   *
   * @param id
   *          id program to delete
   * @return program deleted
   */
  @DeleteMapping(value = Router.DELETE_PROGRAMS + "/{id}")
  public Program delete(@PathVariable Integer id) {
    return programService.delete(id);
  }

  /**
   * Edit a program.
   *
   * @param program
   *          program to edit
   * @return program edited
   */
  @PutMapping(value = Router.EDIT_PROGRAMS)
  public Program edit(@RequestBody Program program) {
    return programService.update(program);
  }

  /**
   * find a program by name.
   *
   * @param name the name
   * @return list of program with a name
   */
  @GetMapping(value = Router.FIND_BY_NAME)
  public ResponseEntity<List<Program>> findByName(@RequestParam String name) {
    List<Program> programs = programService.findByName(name);

    if (programs.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(programs, HttpStatus.OK);
  }

  /**
   * list all programs.
   *
   * @param response
   *          httpResponse
   * @return list of all programs
   */

  @GetMapping(value = Router.FIND_ALL_PROGRAMS)
  public List<Program> findAll(HttpServletResponse response) {
    List<Program> programs = programService.listAll();

    if (programs.isEmpty()) {
      response.setStatus(HttpStatus.NO_CONTENT.value());
    }

    return programs;
  }
}
