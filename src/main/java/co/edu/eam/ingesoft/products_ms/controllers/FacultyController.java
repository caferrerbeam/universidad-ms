package co.edu.eam.ingesoft.products_ms.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.eam.ingesoft.products_ms.model.Faculty;
import co.edu.eam.ingesoft.products_ms.routes.Router;
import co.edu.eam.ingesoft.products_ms.services.FacultyService;

/**
 * REst controller for program entity.
 *
 * @author cristian
 *
 */
@RestController
@RequestMapping(Router.FACULTY_PATH)
public class FacultyController {
  /**
   * faculty service.
   */
  @Autowired
  private FacultyService facultyService;

  /**
   * find a faculty.
   * @param id for faculty to find.
   * @param response httpResponse.
   * @return faculty with id.
   */
  @GetMapping(value = Router.FIND_FACULTY + "/{id}")
  public Faculty find(@PathVariable Integer id, HttpServletResponse response) {
    Faculty faculty = facultyService.find(id);

    if (faculty == null) {
      response.setStatus(HttpStatus.NOT_FOUND.value());
    }

    return faculty;
  }
}
