package com.github.SobolevRoman.restaurantvoting.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishTo extends NamedTo{
    @NotNull
    LocalDate actualDate;

    @Positive
    double price;

    public DishTo(Integer id, String name, LocalDate actualDate, double price){
        super(id, name);
        this.actualDate = actualDate;
        this.price = price;

    }
}
