package com.github.SobolevRoman.restaurantvoting.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuTo extends BaseTo{
    @NotNull
    LocalDate actualDate;

    @NotBlank
    @Size(min = 3, max = 120)
    String description;

    public MenuTo(Integer id, LocalDate actualDate, String description){
        super(id);
        this.actualDate = actualDate;
        this.description = description;
    }
}
