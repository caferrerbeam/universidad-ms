package co.edu.eam.ingesoft.productms.test.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.edu.eam.ingesoft.products_ms.Application;
import co.edu.eam.ingesoft.products_ms.model.Person;
import co.edu.eam.ingesoft.products_ms.repositories.PersonRepository;
import co.edu.eam.ingesoft.products_ms.routes.Router;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { Application.class })
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  public static final String FIND_ALL = Router.PERSON_PATH + Router.FIND_ALL;
  public static final String FIND_BY_NAME = Router.PERSON_PATH + Router.FIND_BY_NAME;
  public static final String FIND_BY_ID = Router.PERSON_PATH + Router.FIND_PERSON;
  public static final String SAVE = Router.PERSON_PATH + Router.CREATE_PERSON;
  public static final String EDIT = Router.PERSON_PATH + Router.EDIT_PERSON;
  public static final String DELETE = Router.PERSON_PATH + Router.DELETE_PERSON;

  @Autowired
  private PersonRepository personRepository;

  @Before
  public void beforeEach() {
    personRepository.deleteAll();
  }

  @Test
  public void listAllTest() throws Exception {

    personRepository.saveAll(Lists.list(new Person(1, "camilo"), new Person(2, "Claudia")));

    mockMvc.perform(get(FIND_ALL)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].name", is("camilo"))).andExpect(jsonPath("$[1].name", is("Claudia")));

  }

  @Test
  public void listAllEmptyTest() throws Exception {
    mockMvc.perform(get(FIND_ALL)).andExpect(status().isNoContent());
  }

  @Test
  public void listByNameEmptyTest() throws Exception {
    mockMvc.perform(get(FIND_BY_NAME + "?name=camilo")).andExpect(status().isNoContent());
  }

  @Test
  public void listByName() throws Exception {

    personRepository.saveAll(Lists.list(new Person(1, "camilo"), new Person(2, "Claudia")));

    mockMvc.perform(get(FIND_BY_NAME + "?name=camilo")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is("camilo")));
  }

  @Test
  public void findById() throws Exception {

    personRepository.saveAll(Lists.list(new Person(1, "camilo"), new Person(2, "Claudia")));

    mockMvc.perform(get(FIND_BY_ID + "/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("camilo")));
  }

  @Test
  public void findByIdNotFound() throws Exception {
    mockMvc.perform(get(FIND_BY_ID + "/1")).andExpect(status().isNotFound());
  }

  @Test
  public void save() throws Exception {
    String content = "{\"name\":\"camilo\",\"id\":1 }";

    mockMvc.perform(post(SAVE).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    Person personToAssert = personRepository.findById(new Integer(1)).get();
    assertEquals("camilo", personToAssert.getName());
    assertEquals(new Integer(1), personToAssert.getId());
  }

  @Test
  public void saveAlreadyExists() throws Exception {
    personRepository.saveAll(Lists.list(new Person(1, "camilo"), new Person(2, "Claudia")));
    String content = "{\"name\":\"camilo\",\"id\":1 }";

    mockMvc.perform(post(SAVE).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(409));
  }

  @Test
  public void edit() throws Exception {
    personRepository.saveAll(Lists.list(new Person(1, "camilo")));

    String content = "{\"name\":\"claudia\",\"id\":1 }";

    mockMvc.perform(put(EDIT).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    Person personToAssert = personRepository.findById(new Integer(1)).get();
    assertEquals("claudia", personToAssert.getName());
    assertEquals(new Integer(1), personToAssert.getId());

  }

  @Test
  public void editNotExists() throws Exception {
    String content = "{\"name\":\"claudia\",\"id\":1 }";

    mockMvc.perform(put(EDIT).content(content).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void del() throws Exception {
    personRepository.saveAll(Lists.list(new Person(1, "camilo")));

    mockMvc.perform(delete(DELETE + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    boolean exits = personRepository.findById(new Integer(1)).isPresent();
    assertFalse(exits);

  }

  @Test
  public void delNotExists() throws Exception {
    mockMvc.perform(delete(DELETE + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
  }

}