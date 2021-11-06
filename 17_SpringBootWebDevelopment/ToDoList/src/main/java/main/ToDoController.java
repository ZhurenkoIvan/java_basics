package main;

import models.ToDo;
import models.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public int add(ToDo toDo) {
        return ToDoList.addToDo(toDo);
    }

    @DeleteMapping("/ToDoList/{id}")
    public void deleteToDo(@PathVariable int id) {
        ToDoList.deleteToDo(id);
    }

    @DeleteMapping("/ToDoList/")
    public void deleteToDoList() {
        ToDoList.deleteToDoList();
    }

    @PutMapping("/ToDoList/{id}{newDate}")
    public ResponseEntity putToDo(@PathVariable int id, @PathVariable Date date) {
        return ToDoList.updateToDo(id, date);
    }
}
