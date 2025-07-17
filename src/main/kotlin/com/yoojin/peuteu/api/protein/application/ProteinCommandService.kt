package com.yoojin.peuteu.api.protein.application

import com.yoojin.peuteu.api.protein.domain.model.Protein
import com.yoojin.peuteu.api.protein.domain.repository.ProteinRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProteinCommandService (
    private val proteinRepository: ProteinRepository
) {
    fun save(name: String, amount: Double) {
        proteinRepository.save(Protein.create(name = name, amount = amount))
    }

    fun update(id: Long, name: String, amount: Double) {
        val protein = proteinRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("단백질 정보가 존재하지 않습니다.")

        protein.name = name
        protein.amount = amount
    }

    fun delete(id: Long) {
        if(!proteinRepository.existsById(id)) {
            throw IllegalArgumentException("삭제할 단백질 정보가 존재하지 않습니다.")
        }

        proteinRepository.delete(id)
    }
}