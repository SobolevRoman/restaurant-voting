package com.github.SobolevRoman.restaurantvoting.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuTo extends BaseTo{
    LocalDate actualDate;

    String description;

    public MenuTo(Integer id, LocalDate actualDate, String description){
        super(id);
        this.actualDate = actualDate;
        this.description = description;
    }
}
