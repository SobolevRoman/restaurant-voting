package com.github.SobolevRoman.restaurantvoting.service;

import com.github.SobolevRoman.restaurantvoting.error.DataConflictException;
import com.github.SobolevRoman.restaurantvoting.model.Restaurant;
import com.github.SobolevRoman.restaurantvoting.model.User;
import com.github.SobolevRoman.restaurantvoting.model.Vote;
import com.github.SobolevRoman.restaurantvoting.repository.RestaurantRepository;
import com.github.SobolevRoman.restaurantvoting.repository.VoteRepository;
import com.github.SobolevRoman.restaurantvoting.to.VoteTo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.SobolevRoman.restaurantvoting.util.validation.ValidationUtil.checkTime;

@Service
@AllArgsConstructor
@Slf4j
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    public List<VoteTo> getAll(int userId) {
        log.info("get all vote");
        return voteRepository.getAll(userId).stream()
                .map(vote -> new VoteTo(vote.id(), vote.getActualDate(), vote.getRestaurant().id()))
                .collect(Collectors.toList());
    }

    public Optional<VoteTo> get(int userId, int id) {
        log.info("get with id = {}", id);
        Vote v = voteRepository.get(userId, id).orElseThrow(
                () -> new DataConflictException("Not found vote with id=" + id));
        return Optional.of(new VoteTo(v.getId(), v.getActualDate(), v.getRestaurant().id()));
    }

    @Transactional
    public Vote prepareAndSave(User user, int restaurantId) {
        checkTime();
        log.info("User {} create vote for restaurant with id={}", user, restaurantId);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new DataConflictException("Not found restaurant with id=" + restaurantId));
        Vote created = new Vote(null, LocalDate.now(), user, restaurant);
        return voteRepository.save(created);
    }

    @Transactional
    public void update(User user, int id) {
        checkTime();
        log.info("User {} update/change his mind for vote with id={}", user, id);
        Vote v = voteRepository.get(user.id(), id).orElseThrow(
                () -> new DataConflictException("Not found vote with id=" + id));
        if (v.getActualDate().compareTo(LocalDate.now()) != 0) {
            throw new DataConflictException("Change mind for vote may be only for this date");
        }
        voteRepository.delete(v);
    }
}
