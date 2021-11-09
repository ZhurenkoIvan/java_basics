package main;

import main.models.ToDo;
import main.models.ToDoRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ToDoList {

    @Autowired
    private ToDoRepository toDoRepository;

    public int addToDo(JSONObject jsonObject) {

        ToDo toDo = new ToDo();
        toDo.setName(jsonObject.getString("name"));
        ToDo newTodo = toDoRepository.save(toDo);
        return newTodo.getId();
    }

    public List<ToDo> getToDoList () {
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        ArrayList<ToDo> toDoArrayList = new ArrayList<>();
        for (ToDo toDo : toDoIterable) {
            toDoArrayList.add(toDo);
        }
        return toDoArrayList;
    }

    public ResponseEntity getToDo(int toDoId) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(toDoId);
        if (!optionalToDo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не найдено");
        }
        return new ResponseEntity(optionalToDo.get(), HttpStatus.OK);
    }

    public void deleteToDo(int toDoId) {
        toDoRepository.deleteById(toDoId);
    }

    public void deleteToDoList() {
        toDoRepository.deleteAll();
    }

    public ResponseEntity updateToDo(int id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isPresent()){
            ToDo newTodo = optionalToDo.get();
            toDoRepository.save(newTodo);
            return new ResponseEntity(newTodo, HttpStatus.OK);
        } return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


}
