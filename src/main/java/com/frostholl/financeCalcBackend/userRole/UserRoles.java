package com.frostholl.financeCalcBackend.userRole;

import com.frostholl.financeCalcBackend.user.User;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "user_roles")
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String roles;

    public UserRoles() {
    }

    public UserRoles(int id, User user, Set<Role> roles) {
        this.id = id;
        this.user = user;
        this.roles = SetToString(roles);
    }

    public UserRoles(User user, Set<Role> roles) {
        this.user = user;
        this.roles = SetToString(roles);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Role> getRoles() {
        return StringToSet();
    }

    public void setRoles(Set<Role> roles) {
        this.roles =  SetToString(roles);
    }

    private String SetToString(Set<Role> roles) {
        StringBuilder sb = new StringBuilder();
        var s = roles.toArray();
        for (int i = 0; i < s.length; i++) {
            if (i != 0)
                sb.append(",");
            sb.append(((Role)s[i]).name());
        }
        return sb.toString();
    }

    private Set<Role> StringToSet() {
        return Collections.singleton(Role.USER);
    }
}
