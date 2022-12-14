package com.github.SobolevRoman.restaurantvoting.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VoteTo extends BaseTo {
    LocalDate actualDate;

    int restaurantId;

    public VoteTo(Integer id, LocalDate actualDate, int restaurantId) {
        super(id);
        this.actualDate = actualDate;
        this.restaurantId = restaurantId;
    }
}
