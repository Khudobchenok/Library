package com.library.config;

import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * Настройки безопасности
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name="userService")
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                //Доступ только для не зарегестрированных пользователей
                .antMatchers("/registration").not().fullyAuthenticated()
                //Доступ только для пользователей с ролью администратор
                .antMatchers("/admin/**", "/addAuthor", "/addBook").hasRole("ADMIN")
                //Доступ разрешен всем пользователям
                .antMatchers("/","/resources/**", "/welcome", "/books/**").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated().and()
                //Настройка для входа в систему
                .formLogin().loginPage("/login")
                //Перенаправление на главную страницу после успешного входа
                .defaultSuccessUrl("/welcome").permitAll().and().logout().permitAll().logoutSuccessUrl("/welcome");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
