package com.ujjwal.quiz.service;

import com.ujjwal.quiz.model.Admin;
import com.ujjwal.quiz.model.Player;
import com.ujjwal.quiz.repository.AdminRepository;
import com.ujjwal.quiz.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            return new org.springframework.security.core.userdetails.User(
                    admin.getUsername(),
                    admin.getPassword(),
                    AuthorityUtils.createAuthorityList(admin.getRole())
            );
        }

        Player player=playerRepository.findByUsername(username);
        if(player!=null){
            return  new org.springframework.security.core.userdetails.User(
                    player.getUsername(),
                    player.getPassword(),
                    AuthorityUtils.createAuthorityList(player.getRole())
            );
        }

        throw  new UsernameNotFoundException("Username not found!"+username);
    }

}
