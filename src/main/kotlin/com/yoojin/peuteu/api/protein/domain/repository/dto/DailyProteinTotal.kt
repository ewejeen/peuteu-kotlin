package com.yoojin.peuteu.api.protein.domain.repository.dto

import java.time.LocalDate

data class DailyProteinTotal(
    val date: LocalDate,
    val total: Double
)
