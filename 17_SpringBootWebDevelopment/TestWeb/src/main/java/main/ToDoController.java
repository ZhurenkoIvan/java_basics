package main;

import model.ToDo;
import model.ToDoList;
import model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

//    @Autowired
//    private ToDoRepository toDoRepository;

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
