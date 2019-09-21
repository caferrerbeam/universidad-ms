package co.edu.eam.ingesoft.products_ms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity program.
 *
 * @author Jhon
 *
 */
@Entity
@Table(name = "programs")
public class Program implements Serializable {

  /**
   * Entity Primary key.
   */
  @Id
  @Column(name = "id")
  private Integer id;

  /**
   * Program`s name.
   */
  @Column(name = "name")
  private String name;

  /**
   * Program`s facultad_id.
   */
  @Column(name = "facultad_id")
  private Integer facultadId;

  /**
   * Instantiates a new program.
   */
  public Program() {
  }

  /**
   * Instantiates a new program.
   *
   * @param id the id
   * @param name the name
   * @param facultadId the facultad id
   */
  public Program(Integer id, String name, Integer facultadId) {
    super();
    this.id = id;
    this.name = name;
    this.facultadId = facultadId;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id          the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the facultad id.
   *
   * @return the falcultad_id
   */
  public Integer getFacultadId() {
    return facultadId;
  }


  /**
   * Sets the facultad id.
   *
   * @param facultadId the new facultad id
   */
  public void setFacultadId(Integer facultadId) {
    this.facultadId = facultadId;
  }

}
