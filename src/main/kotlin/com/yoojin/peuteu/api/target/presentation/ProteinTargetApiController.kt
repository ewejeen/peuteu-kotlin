package com.yoojin.peuteu.api.target.presentation

import com.yoojin.peuteu.api.target.application.ProteinTargetCommandService
import com.yoojin.peuteu.api.target.application.ProteinTargetQueryService
import com.yoojin.peuteu.api.target.presentation.dto.request.ProteinTargetSaveRequest
import org.springframework.web.bind.annotation.*

@RequestMapping("/api")
@RestController
class ProteinTargetApiController(
    private val proteinTargetQueryService: ProteinTargetQueryService,
    private val proteinTargetCommandService: ProteinTargetCommandService
) {
    // 단백질 섭취 목표향 조회
    @GetMapping("/protein-targets")
    fun findProteinTarget(): Double {
        val userId = 1L
        return proteinTargetQueryService.findProteinTarget(userId)
    }

    // 단백질 섭취 목표량 등록
    @PostMapping("/protein-targets")
    fun create(@RequestBody request: ProteinTargetSaveRequest) {
        val userId = 1L
        proteinTargetCommandService.save(request.target, userId)
    }
}