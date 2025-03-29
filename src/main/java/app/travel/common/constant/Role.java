package app.travel.common.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Role {

    TOURIST("TOURIST"),
    FOUNDER("FOUNDER"),
    SUPER_ADMIN("SUPER ADMIN");

    String roleName;

    Role(String roleName){
        this.roleName = roleName;
    }

    public static Role fromRoleName(String name){
        return Arrays.stream(Role.values())
                .filter(
                        role -> role.getRoleName().equalsIgnoreCase(name)
                )
                .findFirst()
                .orElse(TOURIST);
    }

    public static Role fromAuthority(GrantedAuthority authority){

        if(authority == null) return Role.TOURIST;

        String _role = authority.getAuthority().split("ROLE_")[1];

        return Arrays.stream(Role.values())
                .filter(
                        role -> role.name().equalsIgnoreCase(_role)
                )
                .findFirst()
                .orElse(TOURIST);
    }

}
