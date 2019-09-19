package co.edu.eam.ingesoft.products_ms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity program.
 *
 * @author caferrerb
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

  public Program() {
  }

  public Program(Integer id, String name, Integer facultadId) {
	super();
	this.id = id;
	this.name = name;
	this.facultadId = facultadId;
  }

  /**
   * @return the id
   */
  public Integer getId() {
	return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
	this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
	return name;
  }

	/**
	 * @param name the name to set
	 */
  public void setName(String name) {
	this.name = name;
  }

  /**
   * @return the falcultad_id
   */
  public Integer getFacultadId() {
	return facultadId;
  }

  /**
   * @param falcultadId the falcultadId to set
   */
  public void setFacultadId(Integer facultadId) {
	this.facultadId = facultadId;
  }

}
