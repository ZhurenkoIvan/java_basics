package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @GetMapping("/ToDoList/")
    public List<ToDo> getToDoList() {
       List<ToDo> toDoArrayList = ToDoList.getAll();
       return toDoArrayList;
    }
//
    @GetMapping("/ToDoList/{id}")
    public ToDo get(@PathVariable int id) {
        ToDo toDo = ToDoList.get(id);
        return toDo;
    }

    @PostMapping("/ToDoList/{name}")
    public ToDo add(@PathVariable String name) {
        ToDo toDo = ToDoList.add(name);
        return toDo;
    }
//
    @DeleteMapping("/ToDoList/{id}")
    public String deleteToDo(@PathVariable int id) {
        return ToDoList.delete(id);
    }
//
    @DeleteMapping("/ToDoList/")
    public String deleteToDoList() {
        return ToDoList.deleteAll();
    }




}
