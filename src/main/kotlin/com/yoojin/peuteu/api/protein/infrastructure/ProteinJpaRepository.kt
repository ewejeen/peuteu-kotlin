package com.yoojin.peuteu.api.protein.infrastructure

import com.yoojin.peuteu.api.protein.domain.model.Protein
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

import java.time.LocalDateTime

interface ProteinJpaRepository: JpaRepository<Protein, Long> {
    fun findAllByCreatedAtBetween(startDate: LocalDateTime, endDate: LocalDateTime, pageable: Pageable): Page<Protein>

    @Query("SELECT SUM(amount) FROM Protein WHERE createdAt BETWEEN :startDate AND :endDate")
    fun findAmountByCreatedAtBetween(startDate: LocalDateTime, endDate: LocalDateTime): Double
}