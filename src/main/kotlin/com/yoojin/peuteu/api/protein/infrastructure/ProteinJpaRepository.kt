package com.yoojin.peuteu.api.protein.infrastructure

import com.yoojin.peuteu.api.protein.domain.model.Protein
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

import java.time.LocalDateTime

interface ProteinJpaRepository: JpaRepository<Protein, Long> {
    fun findAllByIntakeAtBetween(startDate: LocalDateTime, endDate: LocalDateTime, pageable: Pageable): Page<Protein>

    @Query("SELECT SUM(intake) FROM Protein WHERE intakeAt BETWEEN :startDate AND :endDate")
    fun findIntakeByIntakeAtBetween(startDate: LocalDateTime, endDate: LocalDateTime): Double
}