package com.github.SobolevRoman.restaurantvoting.web.controllers;

import com.github.SobolevRoman.restaurantvoting.model.Vote;
import com.github.SobolevRoman.restaurantvoting.service.VoteService;
import com.github.SobolevRoman.restaurantvoting.to.Rating;
import com.github.SobolevRoman.restaurantvoting.to.VoteTo;
import com.github.SobolevRoman.restaurantvoting.web.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/votes";

    private final VoteService service;

    @GetMapping
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthUser authUser) {
        return service.getAll(authUser.id());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteTo> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        return ResponseEntity.of(service.get(authUser.id(), id));
    }

    @GetMapping("/rating")
    public List<Rating> getRating(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return service.getRating(date);
    }

    @PostMapping
    public ResponseEntity<Vote> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        Vote created = service.prepareAndSave(authUser.getUser(), restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        service.update(authUser.getUser(), id);
    }
}
