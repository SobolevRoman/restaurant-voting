package com.github.SobolevRoman.restaurantvoting.to;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class Rating {
    int restaurantId;
    long rating;

    public Rating(int restaurantId, long rating) {
        this.restaurantId = restaurantId;
        this.rating = rating;
    }
}
