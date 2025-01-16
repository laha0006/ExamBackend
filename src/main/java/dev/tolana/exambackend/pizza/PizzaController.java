package dev.tolana.exambackend.pizza;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class PizzaController {

    private final PizzaService pizzaService;

    @GetMapping("/pizzas")
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }


}
