package main;

import main.models.ToDo;
import main.models.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/ToDoList/")
    public List<ToDo> getToDoList() {
        ToDoList toDoList = new ToDoList(toDoRepository);
        return toDoList.getToDoList();
    }

    @GetMapping("/ToDoList/{id}")
    public ResponseEntity get(@PathVariable int id) {
        ToDoList toDoList = new ToDoList(toDoRepository);
        return toDoList.getToDo(id);
    }

    @PostMapping("/ToDoList/{name}")
    public int add(@PathVariable String name) {
        ToDoList toDoList = new ToDoList(toDoRepository);
        return toDoList.addToDo(name);
    }

    @DeleteMapping("/ToDoList/{id}")
    public void deleteToDo(@PathVariable int id) {
        ToDoList toDoList = new ToDoList(toDoRepository);
        toDoList.deleteToDo(id);
    }

    @DeleteMapping("/ToDoList/")
    public void deleteToDoList() {
        ToDoList toDoList = new ToDoList(toDoRepository);
        toDoList.deleteToDoList();
    }

    @PutMapping("/ToDoList/{id}")
    public ResponseEntity putToDo(@PathVariable int id) {
        ToDoList toDoList = new ToDoList(toDoRepository);
        return toDoList.updateToDo(id);
    }
}
