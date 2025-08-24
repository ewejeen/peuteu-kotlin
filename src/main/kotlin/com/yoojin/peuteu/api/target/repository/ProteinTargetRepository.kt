package com.yoojin.peuteu.api.target.repository

import com.yoojin.peuteu.api.target.domain.model.ProteinTarget
import com.yoojin.peuteu.api.target.infrastructure.ProteinTargetJpaRepository
import com.yoojin.peuteu.api.target.infrastructure.ProteinTargetMyBatisRepository
import org.springframework.stereotype.Repository

@Repository
class ProteinTargetRepository(
    private val proteinTargetJpaRepository: ProteinTargetJpaRepository,
    private val proteinTargetMyBatisRepository: ProteinTargetMyBatisRepository,
) {
    fun save(target: ProteinTarget) {
        proteinTargetJpaRepository.save(target)
    }

    fun findProteinTarget(userId: Long): Double {
        return proteinTargetMyBatisRepository.findProteinTarget(userId)
    }
}