package com.github.SobolevRoman.restaurantvoting.service;

import com.github.SobolevRoman.restaurantvoting.error.DataConflictException;
import com.github.SobolevRoman.restaurantvoting.model.Dish;
import com.github.SobolevRoman.restaurantvoting.repository.DishRepository;
import com.github.SobolevRoman.restaurantvoting.repository.MenuRepository;
import com.github.SobolevRoman.restaurantvoting.to.DishTo;
import com.github.SobolevRoman.restaurantvoting.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DishService {
    private final DishRepository dishRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public void delete(int restaurantId, int menuId, int id) {
        log.info("delete {} by menu {}", id, menuId);
        checkBelong(restaurantId, menuId, id);
        dishRepository.deleteExisted(id);
    }

    @Transactional
    public Optional<Dish> get(int restaurantId, int menuId, int id) {
        log.info("get {} by menu {}", id, menuId);
        checkBelong(restaurantId, menuId, id);
        return dishRepository.findById(id);
    }

    @Transactional
    public List<Dish> getAll(int restaurantId, int menuId) {
        log.info("getAll by menu {}", menuId);
        menuRepository.checkBelong(menuId, restaurantId);
        return dishRepository.getAll(menuId);
    }

    @Transactional
    public Dish save(DishTo to, int restaurantId, int menuId) {
        log.info("create from to {} for restaurant id= {} for menu id= {}", to, restaurantId, menuId);
        menuRepository.checkBelong(menuId, restaurantId);
        return dishRepository.save(prepareToSave(to, menuId));
    }

    @Transactional
    public Dish update(DishTo to, int restaurantId, int menuId, int id) {
        ValidationUtil.assureIdConsistent(to, id);
        checkBelong(restaurantId, menuId, id);
        return prepareToSave(to, menuId);
    }

    private Dish prepareToSave(DishTo to, int menuId) {
        Integer id = to.isNew() ? null : to.getId();
        Dish newDish = new Dish(id, to.getName(), to.getActualDate(), to.getPrice());
        newDish.setMenu(menuRepository.findById(menuId).orElseThrow(
                () -> new DataConflictException("Not found menu with id=" + menuId)));
        return newDish;
    }

    private void checkBelong(int restaurantId, int menuId, int id) {
        menuRepository.checkBelong(menuId, restaurantId);
        dishRepository.checkBelong(id, menuId);
    }
}
