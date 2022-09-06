package com.github.SobolevRoman.restaurantvoting.repository;

import com.github.SobolevRoman.restaurantvoting.model.MenuItem;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MenuItemRepository extends BaseRepository<MenuItem>{
}
