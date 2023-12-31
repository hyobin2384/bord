package com.example.pinterest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final PasswordConfig passwordconfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/", "/member/signUp").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/member/signIn")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    /**
     * Autentication = 인증(로그인)
     * Authorization = 권한(사용자에 따른 권한 부여 -> 관리자, 일반 사용자)
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception{
            auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordconfig.passwordEncoder())
                .usersByUsernameQuery("select username, password, enabled "
                    + "from member "
                    + "where username = ?")
                    .authoritiesByUsernameQuery("select username, role "
                    + "from member "
                    + "where username = ?");
    }
}


