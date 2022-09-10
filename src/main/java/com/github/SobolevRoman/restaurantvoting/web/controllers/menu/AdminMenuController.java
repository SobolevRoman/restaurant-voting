package com.github.SobolevRoman.restaurantvoting.web.controllers.menu;

import com.github.SobolevRoman.restaurantvoting.model.Menu;
import com.github.SobolevRoman.restaurantvoting.service.MenuService;
import com.github.SobolevRoman.restaurantvoting.to.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.github.SobolevRoman.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuController {
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menu";

    @Autowired
    private MenuService service;

    @GetMapping
    public List<Menu> getAll(@PathVariable int restaurantId) {
        return service.getAll(restaurantId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> get(@PathVariable int id, @PathVariable int restaurantId) {
        return ResponseEntity.of(service.getWithDishes(id, restaurantId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id){
        service.delete(id, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody MenuTo to, @PathVariable int restaurantId) {
        checkNew(to);
        Menu created = service.save(to, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
