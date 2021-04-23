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
                .cors().and().csrf().disable() // cors 설정, csrf 비활성화
                .authorizeRequests()
                .antMatchers("/api/map/**").permitAll() // 해당 요청은 모두 사용가능
                .antMatchers("/api/main/**").permitAll() // 해당 요청은 모두 사용가능
                .antMatchers("/api/join").permitAll() // 해당 요청은 모두 사용가능
                .antMatchers("/api/**").permitAll() // 해당 요청은 모두 사용가능
                .antMatchers("/api/andLogin").permitAll() // 안드로이드 로그인
                .antMatchers("/api/profile/{u_id}").permitAll()
                .antMatchers("/api/update/{u_id}").permitAll()
                .antMatchers("/api/findId/{u_email}").permitAll() // 아이디찾기
                .antMatchers("/api/email").permitAll()
                .antMatchers("/api/confirm").permitAll()
                .antMatchers("/api/board/list").permitAll() // 해당 요청은 모두 사용가능
                .anyRequest().authenticated() // antMatchers에 지정되지 않은 요청은 인증된 유저만 사용가능
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement() // 세션 관리
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // 필터 추가
    }
}