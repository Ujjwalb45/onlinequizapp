package com.ujjwal.quiz.service;

import com.ujjwal.quiz.model.Player;
import com.ujjwal.quiz.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements  PlayerService {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void savePlayer(Player player) {
        player.setStatus("ONLINE");
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        playerRepository.save(player);
    }

    @Override
    public void LogoutPlayer() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Player player=playerRepository.findByUsername(authentication.getName());
        if (player != null) {
            player.setStatus("LOGOUT");
            player.setScore(0);
            playerRepository.save(player); // Save the updated player object
        }
    }
}
