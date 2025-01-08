package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository repository;

    //publicar nova comida no cardápio
    // Publicar nova comida no cardápio com suporte a CORS
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        repository.save(foodData);
    }

    //getAll irá pegar todos os dados do banco de dados
    //qunado o endpoint food bater o método GET irá odar esse método
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll(){
        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO:: new).toList();
        return foodList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}") // Mudança para PutMapping e adição do ID na URL
    public void alterFood(@PathVariable Long id, @RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        foodData.setId(id); // Certifique-se de definir o ID do alimento a ser atualizado
        repository.save(foodData);
    }
}
