package aittr.autoproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/autos")
public class AutoController {

    private AutoService service;

    public AutoController(AutoService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllAutos(Model model) {
        List<Auto> autos = service.getAllAutos();
        model.addAttribute("autos", autos);
        return "autos_view";
    }
}
