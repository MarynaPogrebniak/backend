package de.aittr.auto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private AutoService service;

    public OwnerController(AutoService service) {
        this.service = service;
    }

    @GetMapping
    public String getAutosMapping(Model model) {
        model.addAttribute("myListOfAutos", service.getAllAutos());
        return "autos_view";
    }

    @GetMapping("/{auto_owner}") //autos/1
    public String getAutoByOwner(@PathVariable(name = "auto_owner") String owner, Model model) {
        Optional<Auto> optionalAuto = service.getAutoByOwner(owner);
        Auto auto = optionalAuto.get();
        model.addAttribute("auto", auto);
        return "owner";
    }

    @GetMapping("/{auto_ownerAll}") //autos/1
    public String getAllAutosByOwner(@PathVariable(name = "auto_ownerAll") String owner, Model model) {
        Optional<Auto> optionalAuto = service.getAllAutosByOwner(owner);
        Auto auto = optionalAuto.get();
        model.addAttribute("auto", auto);
        return "owner";
    }
}
