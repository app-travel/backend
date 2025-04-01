package app.travel.common.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CookieSameSite {

    STRICT("Strict"),
    LAX("Lax"),
    NONE("None");

    String prettyName;

    CookieSameSite(String prettyName) {
        this.prettyName = prettyName;
    }
}
