package com.github.SobolevRoman.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.SobolevRoman.restaurantvoting.util.validation.NoHtml;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Menu extends BaseEntity {
    @Column(name = "actual_date", nullable = false)
    @NotNull
    private LocalDate actualDate;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 3, max = 120)
    @NoHtml
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ToString.Exclude
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Dish> dishes;

    public Menu(Integer id, LocalDate actualDate, String description) {
        super(id);
        this.actualDate = actualDate;
        this.description = description;
    }
}
