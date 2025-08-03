package com.yoojin.peuteu.api.protein.domain.repository.dto

import java.time.LocalDate

data class ProteinSuccessfulDates(
    val date: LocalDate,
    val total: Double,
    val target: Double,
)
