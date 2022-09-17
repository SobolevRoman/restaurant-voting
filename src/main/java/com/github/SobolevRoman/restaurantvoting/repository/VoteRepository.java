package com.github.SobolevRoman.restaurantvoting.repository;

import com.github.SobolevRoman.restaurantvoting.model.Vote;
import com.github.SobolevRoman.restaurantvoting.to.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.id=:id")
    Optional<Vote> get(int userId, int id);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.actualDate DESC")
    List<Vote> getAll(int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.id=:id AND v.actualDate=CURRENT_DATE")
    Optional<Vote> getByCurrentDate(int userId, int id);

    //https://www.baeldung.com/jpa-queries-custom-result-with-aggregation-functions#solution_constructor
    @Query("""
        SELECT new com.github.SobolevRoman.restaurantvoting.to.Rating(v.restaurant.id, COUNT(v)) 
        FROM Vote v WHERE v.actualDate=:date GROUP BY v.restaurant ORDER BY COUNT(v) DESC 
    """)
    List<Rating> getRatingByDate(LocalDate date);
}
