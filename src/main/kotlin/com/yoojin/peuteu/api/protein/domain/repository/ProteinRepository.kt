package com.yoojin.peuteu.api.protein.domain.repository

import com.yoojin.peuteu.api.protein.domain.model.Protein
import com.yoojin.peuteu.api.protein.domain.repository.dto.DailyProteinTotal
import com.yoojin.peuteu.api.protein.domain.repository.dto.MonthlyProteinTotal
import com.yoojin.peuteu.api.protein.domain.repository.dto.ProteinSuccessfulDates
import com.yoojin.peuteu.api.protein.domain.repository.dto.WeeklyProteinTotal
import com.yoojin.peuteu.api.protein.infrastructure.ProteinJpaRepository
import com.yoojin.peuteu.api.protein.infrastructure.ProteinMyBatisRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
class ProteinRepository(
    private val proteinJpaRepository: ProteinJpaRepository,
    private val proteinMyBatisRepository: ProteinMyBatisRepository
) {
    fun save(protein: Protein) {
        proteinJpaRepository.save(protein)
    }

    fun findAllByDate(start: LocalDateTime, end: LocalDateTime, pageable: Pageable): Page<Protein> {
        return proteinJpaRepository.findAllByIntakeAtBetweenAndDeletedIsFalse(start, end, pageable)
    }

    fun findProteinTotalByDate(start: LocalDateTime, end: LocalDateTime): Double {
        return proteinJpaRepository.findIntakeByIntakeAtBetween(start, end)
    }

    fun findSuccessfulDatesInMonth(year: Int, month: Int): List<ProteinSuccessfulDates> {
        return proteinMyBatisRepository.findSuccessfulDatesInMonth(year, month)
    }

    fun findProteinDailyTotal(startDate: LocalDate, endDate: LocalDate): List<DailyProteinTotal> {
        return proteinMyBatisRepository.findIntakeByIntakeAtBetweenGroupByDaily(startDate, endDate)
    }

    fun findProteinWeeklyTotal(startDate: LocalDate, endDate: LocalDate): List<WeeklyProteinTotal> {
        return proteinMyBatisRepository.findIntakeByIntakeAtBetweenGroupByWeekly(startDate, endDate)
    }

    fun findProteinMonthlyTotal(year: Int): List<MonthlyProteinTotal> {
        return proteinMyBatisRepository.findIntakeByIntakeAtBetweenGroupByMonthly(year)
    }

    fun findByIdOrNull(id: Long): Protein? {
        return proteinJpaRepository.findByIdOrNull(id)
    }
}