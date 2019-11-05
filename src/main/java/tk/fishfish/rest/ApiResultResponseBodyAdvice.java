package tk.fishfish.rest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 返回body处理，包装成ApiResult。如果是ResponseEntity，则不包装
 *
 * @author 奔波儿灞
 * @see tk.fishfish.rest.ApiResult
 * @since 1.0
 */
@RestControllerAdvice(annotations = RestController.class)
public class ApiResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // ResponseEntity，则不包装
        if (body instanceof ResponseEntity) {
            return body;
        }
        // ApiResult，也不包装
        if (body instanceof ApiResult) {
            return body;
        }
        // todo 基本数据类型是否特殊处理
        // 其余统一包装成ApiResult
        return ApiResult.ok(body);
    }

}
