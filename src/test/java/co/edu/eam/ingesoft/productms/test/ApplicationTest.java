package co.edu.eam.ingesoft.productms.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.eam.ingesoft.products_ms.Application;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application-local.properties")
public class ApplicationTest {

  @Test
  public void contextLoads() throws Exception {
  }

}