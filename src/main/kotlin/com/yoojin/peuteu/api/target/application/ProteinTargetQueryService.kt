package com.yoojin.peuteu.api.target.application

import com.yoojin.peuteu.api.target.repository.ProteinTargetRepository
import org.springframework.stereotype.Service

@Service
class ProteinTargetQueryService(
    private val proteinTargetRepository: ProteinTargetRepository
) {
    fun findProteinTarget(userId: Long): Double {
        return proteinTargetRepository.findProteinTarget(userId)
    }
}