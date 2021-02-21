package com.library.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

/**
 * Создание сущности User
 */

//Генерирует get+set
@Data
//Поля класса имеют отображения в БД
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    //Генерацией id будет заниматься БД
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Определение имени столбца
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "login", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    //Поле не будет отображаться в БД
    @Transient
    private String passwordConfirm;
    /*FetchType.EAGER = "жадная" загрузка, список ролей загружается вместе с пользователями,
    не дожидаясь пока к ним обратятся*/
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    /**
     * Конструкторы
     */


    public User() {
    }

    public User(String login, String password) {
        this.username = login;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
