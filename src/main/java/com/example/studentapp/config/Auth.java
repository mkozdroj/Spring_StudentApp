package com.example.studentapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class Auth {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager get() {
        UserDetails user = User.withUsername("test")
                .password(passwordEncoder().encode("test"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/students").hasAnyRole("USER","ADMIN")
                .antMatchers("/tasks").hasAnyRole("ADMIN")
                .antMatchers("/js/**", "/css/**", "/vendor/**").permitAll()
                .antMatchers("/").permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin() // informuje go, że teraz będę konfigurował formularz autoryzacji
                .loginPage("/login") // wskazuje endpoint w którym wyświetlam formularz logowania
                .usernameParameter("username") // nadaje nazwę jaka będzie jako name w inpucie loginu formularza
                .passwordParameter("password")// nadaje nazwę jaka będzie jako name w inpucie hasła formularza
                .loginProcessingUrl("/login")
                .failureForwardUrl("/login?error") // co się stanie w momencie wpisania błędnych danych
                .defaultSuccessUrl("/") // co sięstanie w momencie prawidłowego wpisania danych
                .and()
                .logout()//mówimy springowi, że przechodzimy do obsługi wylogowania
                .logoutSuccessUrl("/") // po wylogowaniu gdzie ma nas przekierować
                .logoutUrl("/logout");
        return http.build();
    }
}