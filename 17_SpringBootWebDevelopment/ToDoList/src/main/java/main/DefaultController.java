package main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class DefaultController {

    @GetMapping("{toDo}/{count}")
    public String index(@PathVariable String toDo,@PathVariable int count) {
        String result = " ";
        for (int i = 0; i < count; i++) {
            result.concat(toDo);
        }
        return result;
    }
}
