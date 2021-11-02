package models;

import java.util.Date;

public class ToDo {
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

    public void setWhenToDo(Date whenToDo) {
        this.whenToDo = whenToDo;
    }
}
