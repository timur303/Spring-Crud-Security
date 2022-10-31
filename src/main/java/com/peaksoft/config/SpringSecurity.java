package com.peaksoft.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SpringSecurity  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("timur").password("timur").roles("Director"))
                .withUser(userBuilder.username("asan").password("asan").roles("Admin"))
                .withUser(userBuilder.username("uson").password("uson").roles("Teacher"))
                .withUser(userBuilder.username("talant").password("talant").roles("Student"));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").hasAnyRole("Director","Admin","Teacher","Student")
                .antMatchers("/director_info").hasAnyRole("Director","Admin","Teacher")
                .antMatchers("/admin_info").hasAnyRole("Admin","Teacher")
                .antMatchers("/teacher_info").hasRole("Teacher")
                .and().formLogin().permitAll();
        super.configure(http);
    }
}
