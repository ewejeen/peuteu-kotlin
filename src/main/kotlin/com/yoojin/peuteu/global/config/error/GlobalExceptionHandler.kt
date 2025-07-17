package com.yoojin.peuteu.global.config.error

import com.yoojin.peuteu.global.config.aop.CommonWrapperResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    /*@ExceptionHandler(MyCustomException::class)
    fun handleCustom(e: MyCustomException): ResponseEntity<CommonWrapperResponse<String>> {
        return ResponseEntity
            .status(e.status)
            .body(CommonWrapperResponse(status = e.status.value(), message = e.message ?: "error", data = null))
    }*/

    @ExceptionHandler(Exception::class)
    fun handleDefault(e: Exception): ResponseEntity<CommonWrapperResponse<String>> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(CommonWrapperResponse(status = 500, message = e.message ?: "unexpected error", data = null))
    }
}
