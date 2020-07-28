package sample;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "Departments", propOrder = { "id", "name"})
public class Departments {

  private long id;
  private String name;

  public Departments(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Departments(){};

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.id + "|" +this.name;
  }
}
