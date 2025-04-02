package app.travel.config.middleware.interceptor;

import app.travel.common.annotation.DefaultMessage;
import app.travel.shared.payload.response.ResultApiResponse;
import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.nio.charset.StandardCharsets;

@Slf4j(topic = "MIDDLEWARE-VOID-RESPONSE-INTERCEPTOR")
//@Configuration
public class MiddlewareVoidResponseInterceptorConfig implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("Middleware void response interceptor: preHandle");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("Middleware void response interceptor: postHandle");

        if(!(handler instanceof HandlerMethod handlerMethod)) return;

        Class<?> controllerClass = handlerMethod.getBeanType();

        RestController restControllerAnnotation = controllerClass.getAnnotation(RestController.class);

        if(restControllerAnnotation == null) return;

        MethodParameter method = handlerMethod.getReturnType();

        if(!void.class.equals(method.getParameterType())) return;

        DefaultMessage defaultMessage = method.getMethodAnnotation(DefaultMessage.class);

//        ResponseStatus responseStatus = method.getAnnotation(ResponseStatus.class);
//
//        HttpStatus statusResponse = responseStatus != null ? responseStatus.value() : AppUtil.statusOfMethod(HttpMethod.valueOf(request.getMethod()));

        HttpStatus statusResponse = HttpStatus.OK;

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(
                Json.pretty(
                        ResultApiResponse.builder()
                                .success(Boolean.TRUE)
                                .message(defaultMessage != null ? defaultMessage.message() : "")
                                .status(statusResponse.getReasonPhrase())
                                .code(statusResponse.value())
                                .path(String.format("%s%s", request.getContextPath(), request.getServletPath()))
                                .build()
                )
        );
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("Middleware void response interceptor: afterCompletion");

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
