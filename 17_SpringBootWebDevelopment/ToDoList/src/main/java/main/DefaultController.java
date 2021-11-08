package main;

import main.models.ToDo;
import main.models.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    private ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String todolist(Model model) {
        Iterable<ToDo> iterable = toDoRepository.findAll();
        ArrayList<ToDo> toDoArrayList = new ArrayList<>();
        for (ToDo toDo : iterable) {
            toDoArrayList.add(toDo);
        }
        model.addAttribute("toDoArrayList", toDoArrayList);

        return "todolist";
    }
}
