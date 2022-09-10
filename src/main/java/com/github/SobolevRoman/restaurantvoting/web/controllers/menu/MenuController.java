package com.github.SobolevRoman.restaurantvoting.web.controllers.menu;

import com.github.SobolevRoman.restaurantvoting.model.Menu;
import com.github.SobolevRoman.restaurantvoting.service.MenuService;
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
public class MenuController {
    static final String REST_URL = "/api/restaurants/{restaurantId}/menu";

    @Autowired
    private MenuService service;

    @GetMapping
    public List<Menu> getAll(@PathVariable int restaurantId) {
        return service.getAll(restaurantId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getWithDishes(@PathVariable int restaurantId, @PathVariable int id) {
        return ResponseEntity.of(service.getWithDishes(id, restaurantId));
    }
}
