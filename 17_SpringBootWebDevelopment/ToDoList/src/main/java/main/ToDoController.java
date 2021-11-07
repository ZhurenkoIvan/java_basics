package main;

import models.ToDo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ToDoController {

    @GetMapping("/ToDoList/")
    public List<ToDo> getToDoList() {
        return ToDoList.getToDoList();
    }

    @GetMapping("/ToDoList/{id}")
    public ResponseEntity get(@PathVariable int id) {
       return ToDoList.getToDo(id);
    }

    @PostMapping("/ToDoList/")
    public int add(String name) {
        return ToDoList.addToDo(name);
    }

    @DeleteMapping("/ToDoList/{id}")
    public void deleteToDo(@PathVariable int id) {
        ToDoList.deleteToDo(id);
    }

    @DeleteMapping("/ToDoList/")
    public void deleteToDoList() {
        ToDoList.deleteToDoList();
    }

    @PutMapping("/ToDoList/{id}")
    public ResponseEntity putToDo(@PathVariable int id) {
        return ToDoList.updateToDo(id);
    }
}
