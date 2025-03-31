package app.travel.common.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum JwtTokenType {

    ACCESS("Access token", "access_token"),
    REFRESH("Refresh token", "refresh_token");

    String name;
    String nameSpecial;

    JwtTokenType(String name, String nameSpecial){
        this.name = name;
        this.nameSpecial = nameSpecial;
    }

}
