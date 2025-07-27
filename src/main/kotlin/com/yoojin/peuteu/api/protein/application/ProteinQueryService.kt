package com.yoojin.peuteu.api.protein.application

import com.yoojin.peuteu.api.protein.domain.repository.ProteinRepository
import com.yoojin.peuteu.api.protein.presentation.dto.response.ProteinResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ProteinQueryService(
    private val proteinRepository: ProteinRepository
) {
    fun findAllByDate(date: String, pageable: Pageable): Page<ProteinResponse> {
        val dates = parseDate(date)
        val proteins = proteinRepository.findAllByDate(dates[0], dates[1], pageable)
        return proteins.map { ProteinResponse.from(it) }
    }

    fun findProteinTotalByDate(date: String): Double {
        val dates = parseDate(date)
        return proteinRepository.findProteinTotalByDate(dates[0], dates[1])
    }

    fun findSuccessfulDatesInMonth(year: Int, month: Int): List<String> {
        // 날짜별 프로틴 섭취 조회

        // 개인 프로틴 목표량 조회

        // 결과값 가공
        //return proteinRepository.findSuccessfulDatesInMonth(year, month)
        return listOf("2025-07-01", "2025-07-02", "2025-07-21")
    }

    private fun parseDate(date: String): List<LocalDateTime> {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val parsedDate = LocalDate.parse(date, formatter)

        val startOfDay = parsedDate.atStartOfDay()
        val endOfDay = parsedDate.plusDays(1).atStartOfDay()

        return listOf(startOfDay, endOfDay)
    }
}