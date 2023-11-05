package com.frostholl.financeCalcBackend.user;

import com.frostholl.financeCalcBackend.userRole.Role;
import com.frostholl.financeCalcBackend.userRole.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserRolesService userRolesService;


    public UserService(UserRepository userRepository, UserRolesService service) {
        this.userRepository = userRepository;
        this.userRolesService = service;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByLogin(String login) {
        Optional<User> userOptional = userRepository.findUserByLogin(login);
        return userOptional.orElse(null);
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByLogin(user.getLogin());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("User with this login already exists!");
        }
        userRepository.save(user);
    }

    public Set<Role> getUserRoles (User user) {
        return userRolesService.getUserRolesByUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByLogin(username);
        if (user.isPresent())
            return user.get();
        System.out.println("User '" + username + "' was not found");
        throw new UsernameNotFoundException("User " + username + " was not found");
    }
}
