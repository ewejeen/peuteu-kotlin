package com.yoojin.peuteu.api.target.application

import com.yoojin.peuteu.api.target.domain.model.ProteinTarget
import com.yoojin.peuteu.api.target.repository.ProteinTargetRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProteinTargetCommandService (
    private val proteinTargetRepository: ProteinTargetRepository
) {
    fun save(target: Double, userId: Long) {
        proteinTargetRepository.save(ProteinTarget.create(target = target, userId = userId))
    }
}