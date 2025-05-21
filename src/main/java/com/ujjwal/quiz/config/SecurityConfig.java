package com.ujjwal.quiz.config;


import com.ujjwal.quiz.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends  WebSecurityConfigurerAdapter{

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    CorsConfig corsConfig;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().
                and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/registerPlayer", "/loginPlayer", "/registerAdmin", "/loginAdmin","/v3/api-docs/**","/swagger-ui/**","/v2/api-docs/**","/swagger-resources/**").permitAll()
                //   .antMatchers("/api/auth/health").hasAuthority("ADMIN")
                .antMatchers("/question/start").authenticated()
                .anyRequest().authenticated()
                .and()
                .cors(c->c.configurationSource(corsConfig))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

