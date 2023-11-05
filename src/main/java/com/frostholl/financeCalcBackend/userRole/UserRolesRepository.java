package com.frostholl.financeCalcBackend.userRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {

    @Query("SELECT ur from UserRoles ur WHERE ur.id = ?1")
    Optional<UserRoles> findUserRolesByID(Integer id);
}
