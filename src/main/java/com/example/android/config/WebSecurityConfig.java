package com.example.android.config;


import com.example.android.Service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);
        auth
                .userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable() // cors ??????, csrf ????????????
                .authorizeRequests()
                .antMatchers("/api/map/**").permitAll() // ?????? ????????? ?????? ????????????
                .antMatchers("/api/main/**").permitAll() // ?????? ????????? ?????? ????????????
                .antMatchers("/api/join").permitAll() // ?????? ????????? ?????? ????????????
                .antMatchers("/api/**").permitAll() // ?????? ????????? ?????? ????????????
                .antMatchers("/api/andLogin").permitAll() // ??????????????? ?????????
                .antMatchers("/api/profile/{u_id}").permitAll()
                .antMatchers("/api/update/{u_id}").permitAll()
                .antMatchers("/api/findId/{u_email}").permitAll() // ???????????????
                .antMatchers("/api/email").permitAll()
                .antMatchers("/api/confirm").permitAll()
                .antMatchers("/api/board/list").permitAll() // ?????? ????????? ?????? ????????????
                .anyRequest().authenticated() // antMatchers??? ???????????? ?????? ????????? ????????? ????????? ????????????
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement() // ?????? ??????
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // ?????? ??????
    }
}