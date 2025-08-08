package com.yoojin.peuteu.api.protein.infrastructure

import com.yoojin.peuteu.api.protein.domain.model.Protein
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

import java.time.LocalDateTime
import java.util.*

interface ProteinJpaRepository: JpaRepository<Protein, Long> {
    fun findAllByIntakeAtBetweenAndDeletedIsFalse(startDate: LocalDateTime, endDate: LocalDateTime, pageable: Pageable): Page<Protein>

    @Query("SELECT SUM(intake) FROM Protein WHERE intakeAt BETWEEN :startDate AND :endDate AND deleted = false")
    fun findIntakeByIntakeAtBetween(startDate: LocalDateTime, endDate: LocalDateTime): Double

    @Query("""
        SELECT DISTINCT cast(intakeAt as date) FROM Protein 
        WHERE EXTRACT(year from intakeAt) = :year AND EXTRACT(month from intakeAt) = :month AND deleted = false
        ORDER BY CAST(intakeAt AS date)
    """)
    fun findRecordedDatesInMonth(year: Int, month: Int): List<Date>
}