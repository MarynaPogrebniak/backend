package de.aittr.dogmvcproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dogs")
public class DogController {

    private DogService service;

    public DogController(DogService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllDogs(Model model) {
        List<Dog> dogs = service.findAll();
        model.addAttribute("dogs", dogs);
        return "dogs_view";
    }

}
