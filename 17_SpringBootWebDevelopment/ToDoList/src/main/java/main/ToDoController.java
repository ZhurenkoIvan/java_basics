package main;

import models.ToDo;
import models.ToDoList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
public class ToDoController {

    @GetMapping("/ToDoList/")
    public List<ToDo> getToDoList() {
        return ToDoList.getToDoList();
    }

    @GetMapping("/ToDoList/{id}")
    public ResponseEntity get(@PathVariable int id) {
        ToDo toDo = ToDoList.getToDo(id);
        if (toDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(toDo, HttpStatus.OK);
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
        ToDo toDo = ToDoList.updateToDo(id, date);
        if (toDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(toDo, HttpStatus.OK);
    }
}
