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

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/ToDoList/")
    public List<ToDo> getToDoList() {
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        ArrayList<ToDo> toDoArrayList = new ArrayList<>();
        for (ToDo toDo : toDoIterable) {
            toDoArrayList.add(toDo);
        }
        return toDoArrayList;
    }

    @GetMapping("/ToDoList/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (!optionalToDo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalToDo.get(), HttpStatus.OK);
    }

    @PostMapping("/ToDoList/")
    public int add(ToDo toDo) {
        ToDo newTodo = toDoRepository.save(toDo);
        return newTodo.getId();
    }

    @DeleteMapping("/ToDoList/{id}")
    public void deleteToDo(@PathVariable int id) {
        toDoRepository.deleteById(id);
    }

    @DeleteMapping("/ToDoList/")
    public void deleteToDoList() {
        toDoRepository.deleteAll();
    }

    @PutMapping("/ToDoList/{id}{newDate}")
    public ResponseEntity putToDo(@PathVariable int id, @PathVariable Date date) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isPresent()){
            ToDo newTodo = optionalToDo.get();
           newTodo.setWhenToDo(date);
           toDoRepository.save(newTodo);
            return new ResponseEntity(newTodo, HttpStatus.OK);
        } return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
