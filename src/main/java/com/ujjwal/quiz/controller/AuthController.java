package com.ujjwal.quiz.controller;

import com.ujjwal.quiz.dto.AdminDTO;
import com.ujjwal.quiz.dto.PlayerDTO;
import com.ujjwal.quiz.model.Admin;
import com.ujjwal.quiz.model.Player;
import com.ujjwal.quiz.repository.PlayerRepository;
import com.ujjwal.quiz.response.ApiResponse;
import com.ujjwal.quiz.service.AdminService;
import com.ujjwal.quiz.service.CustomUserDetailsService;
import com.ujjwal.quiz.service.PlayerService;
import com.ujjwal.quiz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    PlayerService playerService;
    @Autowired
    AdminService adminService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PlayerRepository playerRepository;

    @PostMapping("/registerPlayer")
    public ResponseEntity<ApiResponse> registerPlayer(@RequestBody Player player){
        playerService.savePlayer(player);
        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Player Registered").build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<ApiResponse> registerAdmin(@RequestBody Admin admin){
        adminService.saveAdmin(admin);
        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Admin Registered").build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/loginPlayer")
    public ResponseEntity<ApiResponse> loginPlayer(@RequestBody PlayerDTO playerDTO){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(playerDTO.getUsername(),playerDTO.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            ApiResponse apiResponse = ApiResponse.builder().statusCode(HttpStatus.UNAUTHORIZED.value()).build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
        }
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(playerDTO.getUsername());
        String role=userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("NULL");

        String token =jwtUtil.generateToken(userDetails.getUsername(),role);

        Player player=playerRepository.findByUsername(playerDTO.getUsername());
        if(player!=null){
            player.setStatus("LOGIN");
            playerRepository.save(player);
        }



        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Successfully Token Generated").token(token).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/logoutPlayer")
    public ResponseEntity<ApiResponse> logoutPlayer(){
        playerService.LogoutPlayer();
        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Logout Successfully!").build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @PostMapping("/loginAdmin")
    public ResponseEntity<ApiResponse> loginAdmin(@RequestBody AdminDTO adminDTO){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(adminDTO.getUsername(),adminDTO.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            ApiResponse apiResponse = ApiResponse.builder().statusCode(HttpStatus.UNAUTHORIZED.value()).build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
        }
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(adminDTO.getUsername());
        String role=userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("NULL");

        String token =jwtUtil.generateToken(userDetails.getUsername(),role);

        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Successfully Token Generated").token(token).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
