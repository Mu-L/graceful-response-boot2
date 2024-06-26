package com.feiniaojin.gracefulresponse.advice;

import com.feiniaojin.gracefulresponse.api.ResponseFactory;
import javax.annotation.Resource;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 空返回值的拦截处理.
 *
 * @author <a href="mailto:943868899@qq.com">Yujie</a>
 * @version 0.1
 * @since 0.1
 */
@ControllerAdvice
@Order(value = 1000)
public class GrVoidResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private ResponseFactory responseFactory;
    @Resource
    private AdviceSupport adviceSupport;

    /**
     * 只处理返回空的Controller方法.
     *
     * @param methodParameter 返回类型
     * @param clazz           消息转换器
     * @return 是否对这种返回值进行处理
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> clazz) {

        return Objects.requireNonNull(methodParameter.getMethod()).getReturnType().equals(Void.TYPE)
                && adviceSupport.isJsonHttpMessageConverter(clazz);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        return responseFactory.newSuccessInstance();
    }
}
