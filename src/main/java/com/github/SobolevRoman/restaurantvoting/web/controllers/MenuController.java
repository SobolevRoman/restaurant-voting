package com.github.SobolevRoman.restaurantvoting.web.controllers;

import com.github.SobolevRoman.restaurantvoting.model.Menu;
import com.github.SobolevRoman.restaurantvoting.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class MenuController {
    static final String REST_URL = "/api/restaurants/{restaurantId}/menu";

    @Autowired
    protected MenuRepository repository;

    @GetMapping
    public List<Menu> getAll(@PathVariable int restaurantId) {
        log.info("get all by restaurant {}", restaurantId);
        return repository.getByRestaurantId(restaurantId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getWithDishes(@PathVariable int restaurantId, @PathVariable int id){
        log.info("get {} by restaurant {}", id, restaurantId);
        return ResponseEntity.of(repository.getWithDishes(id));
    }
}
