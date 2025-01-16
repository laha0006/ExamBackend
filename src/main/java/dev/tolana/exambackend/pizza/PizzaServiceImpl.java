package dev.tolana.exambackend.pizza;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;


    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }
}
