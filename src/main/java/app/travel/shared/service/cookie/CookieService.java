package app.travel.shared.service.cookie;


import app.travel.advice.exception.templates.ErrorHolderException;
import app.travel.common.constant.Error;
import app.travel.shared.payload.transfer.CookieTransfer;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CookieService implements ICookieService{

    @Override
    public Boolean sendCookieInclude(CookieTransfer cookieTransfer, HttpServletResponse response) {

        if (cookieTransfer == null) return Boolean.TRUE;

        if(cookieTransfer.getKey() == null || cookieTransfer.getValue().isBlank())
            return Boolean.FALSE;

        Cookie cookie = new Cookie(cookieTransfer.getKey(), cookieTransfer.getValue());

        cookie.setHttpOnly(Boolean.TRUE);
        cookie.setSecure(Boolean.TRUE);
        cookie.setPath(cookieTransfer.getPath());
        cookie.setMaxAge(cookieTransfer.getMaxAge());

        response.addCookie(cookie);

        return Boolean.TRUE;
    }

    @Override
    public Cookie getCookieFrom(HttpServletRequest request, String key) {

        Cookie[] cookies = request.getCookies();

        if(cookies == null)
            throw new ErrorHolderException(Error.COOKIE_NOT_EXISTED);

        Cookie cookie = Arrays.stream(cookies)
                .filter(
                        c -> c.getName().equals(key)
                ).findFirst().orElse(null);

        if(cookie == null)
            throw new ErrorHolderException(Error.COOKIE_NOT_EXISTED);

        return cookie;
    }

}
