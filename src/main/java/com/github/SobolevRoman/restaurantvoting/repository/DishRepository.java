package com.github.SobolevRoman.restaurantvoting.repository;

import com.github.SobolevRoman.restaurantvoting.error.DataConflictException;
import com.github.SobolevRoman.restaurantvoting.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
   /* @Query("SELECT d FROM Dish d WHERE d.menu.id=:menuId AND d.actualDate=:actual")
    List<Dish> getByDateForMenu(int menuId, LocalDate actual);*/

    @Query("SELECT d FROM Dish d WHERE d.menu.id=:menuId")
    List<Dish> getAll(int menuId);

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.menu.id=:menuId")
    Optional<Dish> get(int id, int menuId);

    default Dish checkBelong(int id, int menuId) {
        return get(id, menuId).orElseThrow(
                () -> new DataConflictException("Dish id=" + id + " doesn't belong to Menu id=" + menuId)
        );
    }
}
