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
import co.edu.eam.ingesoft.products_ms.model.Program;
import co.edu.eam.ingesoft.products_ms.repositories.ProgramRepository;
import co.edu.eam.ingesoft.products_ms.routes.Router;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { Application.class })
@TestPropertySource(locations = "classpath:application-local.properties")
public class ProgramControllerTest {

  @Autowired
  private MockMvc mockMvc;

  public static final String FIND_ALL_PROGRAMS = Router.PROGRAMS_PATH + Router.FIND_ALL_PROGRAMS;
  public static final String FIND_BY_NAME = Router.PROGRAMS_PATH + Router.FIND_BY_NAME;
  public static final String FIND_BY_ID = Router.PROGRAMS_PATH + Router.FIND_PROGRAMS;
  public static final String SAVE = Router.PROGRAMS_PATH + Router.CREATE_PROGRAMS;
  public static final String EDIT = Router.PROGRAMS_PATH + Router.EDIT_PROGRAMS;
  public static final String DELETE = Router.PROGRAMS_PATH + Router.DELETE_PROGRAMS;

  @Autowired
  private ProgramRepository programRepository;

  @Before
  public void beforeEach() {
	programRepository.deleteAll();
  }

  @Test
  public void listAllTest() throws Exception {

	programRepository.saveAll(Lists.list(new Program(1, "software", 1), new Program(2, "industrial", 2)));

	mockMvc.perform(get(FIND_ALL_PROGRAMS)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].name", is("software"))).andExpect(jsonPath("$[1].name", is("industrial")));

  }

  @Test
  public void listAllEmptyTest() throws Exception {
	mockMvc.perform(get(FIND_ALL_PROGRAMS)).andExpect(status().isNoContent());
  }

  @Test
  public void listByNameEmptyTest() throws Exception {
	mockMvc.perform(get(FIND_BY_NAME + "?name=software")).andExpect(status().isNoContent());
  }

  @Test
  public void listByName() throws Exception {

	programRepository.saveAll(Lists.list(new Program(1, "software", 1), new Program(2, "industrial", 2)));

	mockMvc.perform(get(FIND_BY_NAME + "?name=software")).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].name", is("software")));
  }

  @Test
  public void findById() throws Exception {

	programRepository.saveAll(Lists.list(new Program(1, "software", 1), new Program(2, "industrial", 2)));

	mockMvc.perform(get(FIND_BY_ID + "/1")).andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is("software")));
  }

  @Test
  public void findByIdNotFound() throws Exception {
	mockMvc.perform(get(FIND_BY_ID + "/1")).andExpect(status().isNotFound());
  }

  @Test
  public void save() throws Exception {
	String content = "{\"name\":\"software\",\"id\":1,\"facultadId\":1 }";
	mockMvc.perform(post(SAVE).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	Program programToAssert = programRepository.findById(new Integer(1)).get();
	assertEquals("software", programToAssert.getName());
	assertEquals(new Integer(1), programToAssert.getId());
  }

  @Test
  public void saveAlreadyExists() throws Exception {
	programRepository.saveAll(Lists.list(new Program(1, "software", 1)));
	String content = "{\"name\":\"software\",\"id\":1,\"facultadId\":1 }";

	System.out.println("esss" + content);
	mockMvc.perform(post(SAVE).content(content).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(409));
  }

  @Test
  public void edit() throws Exception {
	programRepository.saveAll(Lists.list(new Program(1, "software", 1)));

	String content = "{\"name\":\"software\",\"id\":1,\"facultadId\":1 }";

	mockMvc.perform(put(EDIT).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	Program programToAssert = programRepository.findById(new Integer(1)).get();
	assertEquals("software", programToAssert.getName());
		assertEquals(new Integer(1), programToAssert.getId());

  }

  @Test
  public void editNotExists() throws Exception {
	String content = "{\"name\":\"software\",\"id\":1,\"facultadId\":1 }";

	mockMvc.perform(put(EDIT).content(content).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
  }

  @Test
  public void del() throws Exception {
	programRepository.saveAll(Lists.list(new Program(1, "software", 1)));
	mockMvc.perform(delete(DELETE + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	boolean exits = programRepository.findById(new Integer(1)).isPresent();
	assertFalse(exits);

  }

  @Test
  public void delNotExists() throws Exception {
	mockMvc.perform(delete(DELETE + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
  }

}