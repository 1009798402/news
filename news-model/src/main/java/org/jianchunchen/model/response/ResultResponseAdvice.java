package org.jianchunchen.model.response;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.AnnotatedElement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jianchun.chen
 * @date 2020/11/27 9:57
 * <p>
 * -----统一处理rest api的返回值包装
 */
@Slf4j
@RestControllerAdvice
public class ResultResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> clazz) {

        boolean isIntercept;
        Class<?> declaringClass = methodParameter.getDeclaringClass();
        isIntercept = declaringClass.getPackage().getName().startsWith("io.bpass.dubai.api");

        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();

        IgnoreResultFormat methodAnnotation = AnnotationUtils
                .findAnnotation(annotatedElement, IgnoreResultFormat.class);
        IgnoreResultFormat classAnnotation = clazz.getAnnotation(IgnoreResultFormat.class);

        if (isIntercept) {
            isIntercept = methodAnnotation == null && classAnnotation == null;
        }

        return isIntercept;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        if (body instanceof Result) {
            return body;
        }
        //string类型的返回值需要特殊处理一下
        if (body instanceof String) {
            return JSON.toJSON(Result.ofSuccess(body)).toString();

        }
        return Result.ofSuccess(body);
    }


    @ExceptionHandler(value = {Exception.class})
    public Result<String> exception(Exception exception) {
        log.error("occur exception", exception);
        Result<String> result = new Result<>();
        result.setSuccess(false);
        result.setTimestamp(LocalDateTime.now());
        result.setErrMsg(exception.getMessage());
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> validationBodyException(MethodArgumentNotValidException exception) {
        log.error("occur exception", exception);
        Result<String> result = new Result<>();
        result.setSuccess(false);
        result.setTimestamp(LocalDateTime.now());
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> listMsg = new ArrayList<>(errors.size());
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                listMsg.add(fieldError.getDefaultMessage());
            });
            result.setErrMsg(String.join(",",listMsg));
        }
        return result;
    }

}
