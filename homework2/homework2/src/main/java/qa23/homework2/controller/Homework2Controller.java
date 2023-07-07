package qa23.homework2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Homework2Controller {

    @RequestMapping(value = "/hellopage", method = RequestMethod.GET)
    public String getMap() {
        return "hello";
    }

    @RequestMapping(value = "/goodbyepage", method = RequestMethod.GET)
    public String getMap1() {
        return "goodbye";
    }
}
