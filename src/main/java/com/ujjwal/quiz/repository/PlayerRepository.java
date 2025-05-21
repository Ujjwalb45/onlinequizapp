package com.ujjwal.quiz.repository;

import com.ujjwal.quiz.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    public Player findByUsername(String username);
}
