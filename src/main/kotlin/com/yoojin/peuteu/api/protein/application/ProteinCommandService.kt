package com.yoojin.peuteu.api.protein.application

import com.yoojin.peuteu.api.protein.domain.model.Protein
import com.yoojin.peuteu.api.protein.domain.repository.ProteinRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class ProteinCommandService (
    private val proteinRepository: ProteinRepository
) {
    fun save(name: String, intake: Double, intakeAt: LocalDateTime, userId: Long) {
        proteinRepository.save(Protein.create(name = name, intake = intake, intakeAt = intakeAt, userId = userId))
    }

    fun update(id: Long, name: String, intake: Double, intakeAt: LocalDateTime) {
        val protein = proteinRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("단백질 정보가 존재하지 않습니다.")

        protein.name = name
        protein.intake = intake
        protein.intakeAt = intakeAt
    }

    fun delete(id: Long) {
        if(!proteinRepository.existsById(id)) {
            throw IllegalArgumentException("삭제할 단백질 정보가 존재하지 않습니다.")
        }

        proteinRepository.delete(id)
    }
}