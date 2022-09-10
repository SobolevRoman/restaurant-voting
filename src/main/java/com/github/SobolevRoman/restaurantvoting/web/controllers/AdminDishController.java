package com.github.SobolevRoman.restaurantvoting.web.controllers;

import com.github.SobolevRoman.restaurantvoting.model.Dish;
import com.github.SobolevRoman.restaurantvoting.service.DishService;
import com.github.SobolevRoman.restaurantvoting.to.DishTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.github.SobolevRoman.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController {
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menu/{menuId}/dish";

    @Autowired
    private DishService service;

    @GetMapping
    public List<Dish> getAll(@PathVariable int restaurantId, @PathVariable int menuId) {
        return service.getAll(restaurantId, menuId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int restaurantId, @PathVariable int menuId, @PathVariable int id) {
        return ResponseEntity.of(service.get(restaurantId, menuId, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int menuId, @PathVariable int id) {
        service.delete(restaurantId, menuId, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo to, @PathVariable int restaurantId, @PathVariable int menuId) {
        checkNew(to);
        Dish created = service.save(to, restaurantId, menuId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/admin/restaurants/" + restaurantId + "/menu/" + menuId + "/dish" + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishTo to, @PathVariable int restaurantId, @PathVariable int menuId, @PathVariable int id) {
        service.update(to, restaurantId, menuId, id);
    }
}
