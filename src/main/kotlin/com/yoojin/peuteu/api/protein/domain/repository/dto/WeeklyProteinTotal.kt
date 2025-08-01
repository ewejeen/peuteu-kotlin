package com.yoojin.peuteu.api.protein.domain.repository.dto

import java.time.LocalDate

data class WeeklyProteinTotal(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val total: Double
)
