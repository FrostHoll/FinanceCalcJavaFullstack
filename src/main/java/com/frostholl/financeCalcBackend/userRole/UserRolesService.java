package com.frostholl.financeCalcBackend.userRole;

import com.frostholl.financeCalcBackend.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserRolesService {
    private final UserRolesRepository userRolesRepository;

    public UserRolesService(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    public Set<Role> getRolesByUser(User user) {
        Optional<UserRoles> roles = userRolesRepository.findUserRolesByID(user.getId());
        if (roles.isPresent())
            return roles.get().getRoles();
        return null;
    }

    public UserRoles getUserRolesByUser(User user) {
        Optional<UserRoles> roles = userRolesRepository.findUserRolesByID(user.getId());
        return roles.orElse(null);
    }

    public void setUserRolesByUser(User user, Set<Role> roles) {
        Optional<UserRoles> userRoles = userRolesRepository.findUserRolesByID(user.getId());
        if (userRoles.isPresent()) {
            userRoles.get().setRoles(roles);
        }
        else {
            var newUserRoles = new UserRoles(user, roles);
            userRolesRepository.save(newUserRoles);
        }
    }
}
