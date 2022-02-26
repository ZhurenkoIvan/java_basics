package main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DefaultController {

    @GetMapping("/admin")
    public String startPage(){
        return "index";
    }

}
