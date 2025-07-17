package com.yoojin.peuteu.global.config.aop

import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice


@RestControllerAdvice
class CommonControllerAdvice : ResponseBodyAdvice<Any> {
    override fun supports(
        returnType: MethodParameter,
        converterType: Class<out HttpMessageConverter<*>>
    ): Boolean {
        // ResponseEntity는 그대로 내보낸다.
        return !returnType.parameterType.isAssignableFrom(ResponseEntity::class.java)
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        // 이미 CommonWrapperResponse로 래핑된 경우 그대로 반환
        if (body is CommonWrapperResponse<*>) return body

        // 상태코드 추출 (Servlet 환경일 경우만 가능)
        val httpStatus = (response as? ServletServerHttpResponse)
            ?.servletResponse
            ?.status
            ?.let { HttpStatus.resolve(it) }
            ?: HttpStatus.OK

        return CommonWrapperResponse(
            status = httpStatus.value(),
            data = body
        )
    }

}