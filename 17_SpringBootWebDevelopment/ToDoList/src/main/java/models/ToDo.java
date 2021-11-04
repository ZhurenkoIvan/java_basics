package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Date whenToDo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getWhenToDo() {
        return whenToDo;
    }

    public int getId() {
        return id;
    }


    public void setWhenToDo(Date whenToDo) {
        this.whenToDo = whenToDo;
    }

}
