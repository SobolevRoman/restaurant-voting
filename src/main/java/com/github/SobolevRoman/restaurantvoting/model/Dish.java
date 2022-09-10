package com.github.SobolevRoman.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(
        columnNames = {"name", "menu_id"}, name = "dish_menu_idx"
)})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Dish extends NamedEntity {
    @Column(name = "actual_date", nullable = false)
    @NotNull
    private LocalDate actualDate;

    @Column(name = "price", nullable = false)
    @Positive
    private double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Menu menu;

    public Dish(Integer id, String name, LocalDate actualDate, double price) {
        super(id, name);
        this.actualDate = actualDate;
        this.price = price;
    }
}
