package com.yoojin.peuteu.api.protein.presentation.dto.request

import java.time.LocalDateTime

class ProteinSaveRequest(
    val name: String,
    val intake: Double,
    val intakeAt: LocalDateTime,
) {
}