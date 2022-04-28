package se.iths.storemanagementsystem.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.iths.storemanagementsystem.entity.RoleEntity;
import se.iths.storemanagementsystem.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class StorePrincipal implements UserDetails {
    private final UserEntity userEntity;

    public StorePrincipal(UserEntity userEntity) {
        this.userEntity = StorePrincipal.this.userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<RoleEntity> roles = userEntity.getRoles();
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles.size());

        for (RoleEntity role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
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

