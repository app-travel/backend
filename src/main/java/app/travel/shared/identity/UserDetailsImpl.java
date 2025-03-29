package app.travel.shared.identity;

import app.travel.model.roles.RoleEntity;
import app.travel.model.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsImpl implements UserDetails {

    transient UserEntity user;

    transient RoleEntity role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new UserDetailsImpl.AuthorityImpl(role));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }


    @Builder
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class AuthorityImpl implements GrantedAuthority {

        transient RoleEntity role;

        @Override
        public String getAuthority() {
            return String.format("ROLE_%s", role.getRoleName());
        }
    }
}
