package co.edu.eam.ingesoft.products_ms.routes;

/**
 * Class to define the service's routes.
 *
 * @author Jhon
 *
 */
public class Router {


  /**
   * Base path of this api.
   */
  public static final String BASE_PATH = "/api/universidad-ms";

  /**
   * person uri.
   */
  public static final String PERSON_PATH = BASE_PATH + "/persons";

  /**
   * programs uri.
   */
  public static final String PROGRAMS_PATH = BASE_PATH + "/programs";

  /**
   * programs uri.
   */
  public static final String FACULTY_PATH = BASE_PATH + "/faculty";

  /**
   * create person uri.
   */
  public static final String CREATE_PERSON = "/";

  /**
   * find person uri.
   */
  public static final String FIND_PERSON = "/";

  /**
   * delete person uri.
   */
  public static final String DELETE_PERSON = "/";

  /**
   * Edit person uri.
   */
  public static final String EDIT_PERSON = "/";

  /**
   * find by name persons uri.
   */
  public static final String FIND_BY_NAME = "/find_by_name";

  /**
   * find all person uri.
   */
  public static final String FIND_ALL = "/all";

  /**
   * Edit programs uri.
   */
  public static final String EDIT_PROGRAMS = "/";

  /**
   * create programs uri.
   */
  public static final String CREATE_PROGRAMS = "/";

  /**
   * find programs uri.
   */
  public static final String FIND_PROGRAMS = "/";

  /**
   * delete programs uri.
   */
  public static final String DELETE_PROGRAMS = "/";

  /**
   * find all programs uri.
   */
  public static final String FIND_ALL_PROGRAMS = "/";

  /**
   * find faculty uri.
   */
  public static final String FIND_FACULTY = "/";

}
