package com.yoojin.peuteu.api.protein.domain.repository

import com.yoojin.peuteu.api.protein.domain.model.Protein
import com.yoojin.peuteu.api.protein.domain.repository.dto.DailyProteinTotal
import com.yoojin.peuteu.api.protein.infrastructure.ProteinJpaRepository
import com.yoojin.peuteu.api.protein.infrastructure.ProteinMyBatisRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
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
        return proteinJpaRepository.findAllByCreatedAtBetween(start, end, pageable)
    }

    fun findProteinTotalByDate(start: LocalDateTime, end: LocalDateTime): Double {
        return proteinJpaRepository.findAmountByCreatedAtBetween(start, end)
    }

    fun findProteinDailyTotal(startDate: LocalDateTime, endDate: LocalDateTime): List<DailyProteinTotal> {
        return proteinMyBatisRepository.findAmountByCreatedAtBetweenGroupByDaily(startDate, endDate)
    }

    fun findByIdOrNull(id: Long): Protein? {
        return proteinJpaRepository.findByIdOrNull(id)
    }

    fun existsById(id: Long): Boolean {
        return proteinJpaRepository.existsById(id)
    }

    fun delete(id: Long) {
        proteinJpaRepository.deleteById(id)
    }
}