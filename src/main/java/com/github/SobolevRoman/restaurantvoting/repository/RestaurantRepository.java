package com.github.SobolevRoman.restaurantvoting.repository;

import com.github.SobolevRoman.restaurantvoting.model.Restaurant;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant>{
}
