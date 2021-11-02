package models;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ToDoList {

    private static int currentId = 1;
    private static HashMap<Integer, ToDo> toDoList = new HashMap<>();

    public synchronized static int addToDo(ToDo toDo) {
        int id = currentId++;
        toDoList.put(id, toDo);
        return id;
    }

    public static List<ToDo> getToDoList () {
        return new ArrayList<>(toDoList.values());
    }

    public static ToDo getToDo(int toDoId) {
        return toDoList.getOrDefault(toDoId, null);
    }

    public synchronized static void deleteToDo(int toDoId) {
        if (toDoList.containsKey(toDoId)) {
            toDoList.remove(toDoId);
        }
    }

    public synchronized static void deleteToDoList() {
        toDoList.clear();
    }

    public synchronized static ToDo updateToDo(int id, Date date) {
        ToDo toDo = toDoList.get(id);
        toDo.setWhenToDo(date);
        return toDo;
    }


}
