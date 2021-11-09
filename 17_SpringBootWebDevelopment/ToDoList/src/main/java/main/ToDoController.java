package main;

import main.models.ToDo;
import main.models.ToDoRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    ToDoList toDoList;

    @GetMapping("/ToDoList/")
    public List<ToDo> getToDoList() {
        return toDoList.getToDoList();
    }

    @GetMapping("/ToDoList/{id}")
    public ResponseEntity get(@PathVariable int id) {
        return toDoList.getToDo(id);
    }

    @PostMapping("/ToDoList/")
    public int add(@RequestBody String body) {
        JSONObject jsonObject = new JSONObject(body);
        return toDoList.addToDo(jsonObject);
    }

    @DeleteMapping("/ToDoList/{id}")
    public void deleteToDo(@PathVariable int id) {
        toDoList.deleteToDo(id);
    }

    @DeleteMapping("/ToDoList/")
    public void deleteToDoList() {
        toDoList.deleteToDoList();
    }

    @PutMapping("/ToDoList/{id}")
    public ResponseEntity putToDo(@PathVariable int id) {
        return toDoList.updateToDo(id);
    }
}
