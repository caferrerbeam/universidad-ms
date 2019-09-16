package co.edu.eam.ingesoft.products_ms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity person.
 *
 * @author caferrerb
 *
 */
@Entity
@Table(name = "persons")
public class Person implements Serializable {

  /**
   * Entity Primary key.
   */
  @Id
  private Integer id;

  /**
   * Person`s name.
   */
  @Column(name = "name")
  private String name;

  public Person() {
  }

  public Person(Integer id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  /**
   * @return the id
   */
  public final Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public final void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
  }

}
