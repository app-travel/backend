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

    String prettyRoleName;

    Role(String prettyRoleName){
        this.prettyRoleName = prettyRoleName;
    }

    public static Role fromPrettyRoleName(String name){
        return Arrays.stream(Role.values())
                .filter(
                        role -> role.getPrettyRoleName().equalsIgnoreCase(name)
                )
                .findFirst()
                .orElse(TOURIST);
    }

    public static Role fromAuthority(GrantedAuthority authority){

        if(authority == null) return Role.TOURIST;

        if(authority.getAuthority() == null) return Role.TOURIST;

        String roleName =  authority.getAuthority().substring("ROLE_".length()).trim();

        return Arrays.stream(Role.values())
                .filter(
                        role -> role.name().equalsIgnoreCase(roleName)
                )
                .findFirst()
                .orElse(TOURIST);
    }

}
