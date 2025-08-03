package com.yoojin.peuteu.api.protein.presentation.dto.response

import com.yoojin.peuteu.api.protein.domain.model.Protein
import java.time.LocalDateTime

data class ProteinResponse(
    val id: Long,
    val name: String,
    val intake: Double,
    val intakeAt: LocalDateTime
) {
    companion object {
        fun from(entity: Protein): ProteinResponse {
            return ProteinResponse(
                id = entity.id!!,
                name = entity.name,
                intake = entity.intake,
                intakeAt = entity.intakeAt
            )
        }
    }
}