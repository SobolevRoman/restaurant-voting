package com.github.SobolevRoman.restaurantvoting.model;

import com.github.SobolevRoman.restaurantvoting.util.validation.NoHtml;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Restaurant extends NamedEntity{

    @NotBlank
    @Size(min = 1, max = 24)
    @Column(name = "label", unique = true, nullable = false)
    @NoHtml
    private String label;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
