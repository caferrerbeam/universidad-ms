package co.edu.eam.ingesoft.productms.test.controllers;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.edu.eam.ingesoft.products_ms.Application;
import co.edu.eam.ingesoft.products_ms.model.Faculty;
import co.edu.eam.ingesoft.products_ms.repositories.FacultyRepository;
import co.edu.eam.ingesoft.products_ms.routes.Router;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {Application.class})
public class FacultyControllerTest {

  @Autowired
  private MockMvc mockMvc;
  public static final String FIND_BY_ID = Router.FACULTY_PATH
      + Router.FIND_FACULTY;
  @Autowired
  private FacultyRepository facultyRepository;

  @Before
  public void beforeEach() {
    facultyRepository.deleteAll();
  }

  @Test
  public void findById() throws Exception {

    facultyRepository.saveAll(Lists.list(new Faculty(1, "Ingenieria"),
        new Faculty(2, "Administracion")));

    mockMvc.perform(get(FIND_BY_ID + "/1")).andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Ingenieria")));
  }

  @Test
  public void findByIdNotFound() throws Exception {
    mockMvc.perform(get(FIND_BY_ID + "/1")).andExpect(status().isNotFound());
  }
}
