package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ToDoList {
    private static int id = 1;
    private static ConcurrentHashMap<Integer, ToDo> toDoMap = new ConcurrentHashMap<>();

    public static ToDo add(String name) {
        ToDo toDo = new ToDo();
        toDo.setId(id);
        toDo.setName(name);
        toDoMap.put(id, toDo);
        id++;
        return toDo;
    }

    public static ToDo get(int id) {
        return toDoMap.get(id);
    }

    public static String delete(int id) {
        toDoMap.remove(id);
        return "Успешно";
    }

    public static String deleteAll() {
        toDoMap.clear();
        return "Успешно";
    }
    public static List<ToDo> getAll() {
        ArrayList<ToDo> toDoArrayList = new ArrayList<>();
        for(int id: toDoMap.keySet()) {
            toDoArrayList.add(toDoMap.get(id));
        }
        return toDoArrayList;
    }

}
