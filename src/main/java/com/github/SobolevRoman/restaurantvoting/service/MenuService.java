package com.github.SobolevRoman.restaurantvoting.service;

import com.github.SobolevRoman.restaurantvoting.error.DataConflictException;
import com.github.SobolevRoman.restaurantvoting.model.Menu;
import com.github.SobolevRoman.restaurantvoting.repository.DishRepository;
import com.github.SobolevRoman.restaurantvoting.repository.MenuRepository;
import com.github.SobolevRoman.restaurantvoting.repository.RestaurantRepository;
import com.github.SobolevRoman.restaurantvoting.to.MenuTo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MenuService {
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Menu> getAll(int restaurantId) {
        log.info("get all by restaurant {}", restaurantId);
        return menuRepository.getAll(restaurantId);
    }

    @Transactional
    public Optional<Menu> getWithDishes(int id, int restaurantId) {
        log.info("get {} by restaurant {} with dishes", id, restaurantId);
        menuRepository.checkBelong(id, restaurantId);
        return menuRepository.getWithDishes(id);
    }

    @Transactional
    public void delete(int id, int restaurantId){
        log.info("delete {} by restaurant {}", id, restaurantId);
        menuRepository.checkBelong(id, restaurantId);
        menuRepository.deleteExisted(id);
    }

    @Transactional
    public Menu save(MenuTo to, int restaurantId){
        log.info("create from to {}", to);
        return menuRepository.save(prepareToSave(to, restaurantId));
    }

    private Menu prepareToSave(MenuTo to, int restaurantId){
        Menu newMenu = new Menu(null, to.getActualDate(), to.getDescription());
        newMenu.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new DataConflictException("Not found restaurant with id=" + restaurantId)));
        return newMenu;
    }
}
