package com.github.SobolevRoman.restaurantvoting.repository;

import com.github.SobolevRoman.restaurantvoting.error.DataConflictException;
import com.github.SobolevRoman.restaurantvoting.model.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId ORDER BY m.actualDate DESC")
    List<Menu> getAll(int restaurantId);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.id=?1")
    Optional<Menu> getWithDishes(int id);

    @Query("SELECT m FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    Optional<Menu> get(int id, int restaurantId);

    default Menu checkBelong(int id, int restaurantId) {
        return get(id, restaurantId).orElseThrow(
                () -> new DataConflictException("Menu id=" + id + " doesn't belong to Restaurant id=" + restaurantId)
        );
    }
}
