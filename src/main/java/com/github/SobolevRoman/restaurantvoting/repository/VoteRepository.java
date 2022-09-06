package com.github.SobolevRoman.restaurantvoting.repository;

import com.github.SobolevRoman.restaurantvoting.model.Vote;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote>{
}
