package com.github.SobolevRoman.restaurantvoting.web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/votes";
}
