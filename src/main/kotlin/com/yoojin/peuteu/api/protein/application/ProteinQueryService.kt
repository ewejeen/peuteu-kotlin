package com.yoojin.peuteu.api.protein.application

import com.yoojin.peuteu.api.protein.domain.repository.ProteinRepository
import com.yoojin.peuteu.api.protein.presentation.dto.response.ProteinResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

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

    // 날짜 기준별 프로틴 섭취량 조회
    fun findProteinTotalByDateStandard(standard: String): List<Any> {
        when (standard) {
            "week" -> {
                val datePair = getStartAndEndDate(standard)
                return proteinRepository.findProteinDailyTotal(datePair!!.first, datePair.second)
            }
            "month" -> {
                val datePair = getStartAndEndDate(standard)
                return proteinRepository.findProteinWeeklyTotal(datePair!!.first, datePair.second)
            }
            "year" -> {
                val year = LocalDate.now().year
                return proteinRepository.findProteinMonthlyTotal(year)
            }
            else -> error("Invalid standard format")
        }
    }

    // TODO
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

    private fun getStartAndEndDate(standard: String): Pair<LocalDate, LocalDate>? {
        val today = LocalDate.now()

        if (standard == "week") {
            val startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            val endDate = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

            return Pair(startDate, endDate)
        } else if (standard == "month") {
            val firstDayOfMonth = LocalDate.of(today.year, today.monthValue, 1)
            val lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth())

            val startDate = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            val endDate = lastDayOfMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

            return Pair(startDate, endDate)
        }

        return null
    }
}