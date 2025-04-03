package app.travel.advice.response;

import app.travel.common.annotation.DefaultMessage;
import app.travel.shared.payload.response.ResultApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@Slf4j(topic = "GLOBAL-OBJECT-RESPONSE-WRAPPER")
@RestControllerAdvice
public class GlobalObjectResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(
            MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType
    ) {

        // (1) -> Nhận biết hướng đi của request và vị trí của handler response
        Class<?> declaringClass = returnType.getDeclaringClass();
        String packageName = declaringClass.getPackageName();
        Class<?> objectReturnType = returnType.getParameterType();
        // -> end (1)

        log.info("Package: {}", packageName);
        log.info("declaringClass: {}", declaringClass);

        return !packageName.contains("springdoc") &&
                !packageName.contains("swagger") &&
                (
                    declaringClass.getAnnotation(RestController.class) != null ||
                    objectReturnType.equals(ResultApiResponse.ErrorResponse.class)
                );
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {

        if(body instanceof ResultApiResponse.ErrorResponse errorResponse){

            log.error("Error response with message: {}", errorResponse.getMessage());

            response.setStatusCode(HttpStatus.valueOf(errorResponse.getCode()));

            errorResponse.setSuccess(Boolean.FALSE);
            errorResponse.setPath(request.getURI().getPath());

            return errorResponse;
        }

//        Method method = returnType.getMethod();

//        ResponseStatus responseStatus = method.getAnnotation(ResponseStatus.class);
//
//        HttpStatus statusResponse = responseStatus != null ? responseStatus.value() : AppUtil.statusOfMethod(request.getMethod());
//
//        response.setStatusCode(statusResponse);

        DefaultMessage defaultMessage = returnType.getMethodAnnotation(DefaultMessage.class);

        HttpStatus statusResponse = HttpStatus.OK;

        response.setStatusCode(statusResponse);

        log.error("Result api response with message: {}", defaultMessage);

        return ResultApiResponse.builder()
                .success(Boolean.TRUE)
                .message(defaultMessage != null ? defaultMessage.message() : "")
                .status(statusResponse.getReasonPhrase())
                .code(statusResponse.value())
                .path(request.getURI().getPath())
                .data(body)
                .build();
    }

}