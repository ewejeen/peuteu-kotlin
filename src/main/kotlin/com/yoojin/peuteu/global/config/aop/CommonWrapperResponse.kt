package com.yoojin.peuteu.global.config.aop

data class CommonWrapperResponse<T>(
    val status: Int,
    val message: String = "success",
    val data: T?
)