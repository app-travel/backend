package app.travel.advice.response;

import app.travel.common.annotation.DefaultMessage;
import app.travel.shared.payload.response.ResultApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(
            MethodParameter returnType,
            @NonNull Class<? extends HttpMessageConverter<?>> converterType
    ) {

        // (1) -> Nhận biết hướng đi của request và vị trí của handler response
        Class<?> declaringClass = returnType.getDeclaringClass();
        String packageName = declaringClass.getPackageName();
        Class<?> objectReturnType = returnType.getParameterType();

        System.out.println("----------- supports ResponseBodyAdvice -----------");
        System.out.println("packageName: " + packageName);
        System.out.println("declaringClass: " + declaringClass);
        System.out.println("----------- supports ResponseBodyAdvice -----------");
        // -> end (1)

        return !packageName.contains("springdoc") &&
                !packageName.contains("swagger") &&
                (
                   objectReturnType.equals(ResultApiResponse.class) ||
                   objectReturnType.equals(ResultApiResponse.ErrorResponse.class)
                );
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
    ) {

        if(body instanceof ResultApiResponse.ErrorResponse errorResponse){

            response.setStatusCode(errorResponse.getStatus());

            errorResponse.setSuccess(Boolean.FALSE);
            errorResponse.setPath(request.getURI().getPath());

            return errorResponse;
        }

        HttpStatus statusResponse = statusOfMethod(request.getMethod());

        DefaultMessage defaultMessage = returnType.getMethod().getAnnotation(DefaultMessage.class);

        return ResultApiResponse.builder()
                .success(Boolean.TRUE)
                .message(defaultMessage != null ? defaultMessage.message() : "")
                .status(statusResponse)
                .code(statusResponse.value())
                .path(request.getURI().getPath())
                .data(body)
                .build();
    }

    private HttpStatus statusOfMethod(HttpMethod method){

        if(method.equals(HttpMethod.POST))
            return HttpStatus.CREATED;
        else if(method.equals(HttpMethod.PUT) || method.equals(HttpMethod.PATCH))
            return HttpStatus.ACCEPTED;
        else if(method.equals(HttpMethod.DELETE))
            return HttpStatus.NO_CONTENT;
        else return HttpStatus.OK;
    }
}