package de.ait.rentacar.controllers;

import de.ait.rentacar.models.Car;
import de.ait.rentacar.services.CarsService;
import de.ait.rentacar.services.impl.CarsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CarsController {
    private final CarsService carsService; // final чтобы это поле нельзя было менять

    public CarsController (CarsServiceImpl service) {
        this.carsService = service;
    }
    /* @GetMapping("/cars")
    public String getCarsPage(Model model) {
        model.addAttribute("carsList", service.getAllCars());
        return "cars_view";
    } */

    @GetMapping("/cars")
    @ResponseBody // ставим над методом в класее контроллер,
                    // то спринг будет понимать что вы возвращаете
                    // не название view, а тело ответа(данные),
                    // а данные, которые нудно передать клиенту
                    // Таким  оьразом спринг использует специальный конвертер
                    // или serializer, который преоьрузаует обычный
                    // java объект в json
                    // Конвертер Java в JSON и наоборот называется Jackson
    public List<Car> getAllCars() {
        return carsService.getAllCars();
    }
}
