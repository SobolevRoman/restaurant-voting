package com.github.SobolevRoman.restaurantvoting.repository;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<DishRepository>{
}
