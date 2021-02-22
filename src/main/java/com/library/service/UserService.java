package com.library.service;

import com.library.entity.Role;
import com.library.entity.User;
import com.library.repository.RoleRepo;
import com.library.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Создание сервиса для обеспечения приложения бизнес-логикой
 */

//Logger
@Slf4j
@Service
//Интерфейс UserDetailsService необходим для Spring Security
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    /*Чтобы не хранить пароль в «сыром» виде он предварительно хэшируется с
    помощью bCryptPasswordEncoder. Затем новый пользователь сохраняется в БД.*/
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        return userOptional.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public boolean saveUser(User user, Model model) {
        if (user.getUsername().isEmpty()) {
            model.addAttribute("authorError", "Enter username");
            return false;
        }
        boolean onlyNumbers = user.getUsername().matches("^[0-9]+$");
        if (onlyNumbers) {
            model.addAttribute("registrationError", "You can't use username only for numbers");
            return false;
        }
        if (user.getPassword().length() < 4) {
            model.addAttribute("passwordError", "Password must be > 4 symbols");
            return false;
        }
        boolean testUsers = user.getUsername().contains("test");
        if (testUsers) {
            model.addAttribute("registrationError", "You can't use username with \"test\"");
            return false;
        }
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            model.addAttribute("registrationError", "User already add");
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try {
            userRepo.save(user);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("user saved");
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepo.findById(userId).isPresent()) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }

    public boolean addAdmin(Long userId) {
        if (userRepo.findById(userId).isPresent()) {
            User user = userRepo.findUserById(userId);
            /*user.setRoles();
            userRepo.save()*/

            /*em.createQuery("INSERT INTO public.users_roles(user_id, roles_id) VALUES (userId, 2)");*/
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
