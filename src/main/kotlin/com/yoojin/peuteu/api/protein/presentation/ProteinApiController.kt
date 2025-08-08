package com.yoojin.peuteu.api.protein.presentation

import com.yoojin.peuteu.api.protein.application.ProteinCommandService
import com.yoojin.peuteu.api.protein.application.ProteinQueryService
import com.yoojin.peuteu.api.protein.presentation.dto.request.ProteinSaveRequest
import com.yoojin.peuteu.api.protein.presentation.dto.response.ProteinResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/api")
@RestController
class ProteinApiController(
    private val proteinCommandService: ProteinCommandService,
    private val proteinQueryService: ProteinQueryService
) {
    // 단백질 섭취 성공한 날짜 조회
    @GetMapping("/proteins/successful-dates")
    fun findSuccessfulDatesInMonth(year: Int, month: Int): List<String> {
        return proteinQueryService.findSuccessfulDatesInMonth(year, month)
    }

    // 단백질 섭취 정보 있는 날짜 조회
    @GetMapping("/proteins/recorded-dates")
    fun findRecordedDatesInMonth(year: Int, month: Int): List<Date> {
        return proteinQueryService.findRecordedDatesInMonth(year, month)
    }

    @PostMapping("/proteins")
    fun create(@RequestBody request: ProteinSaveRequest) {
        proteinCommandService.save(request.name, request.intake, request.intakeAt, 1)
    }

    @GetMapping("/proteins/{date}")
    fun findAllByDate(@PathVariable date: String, @PageableDefault(size = 10, sort = ["intakeAt"], direction = Sort.Direction.DESC) pageable: Pageable): Page<ProteinResponse> {
        return proteinQueryService.findAllByDate(date, pageable)
    }

    // 날짜별 프로틴 총 섭취량 조회
    @GetMapping("/proteins/total/{date}")
    fun findProteinTotalByDate(@PathVariable date: String): Double {
        return proteinQueryService.findProteinTotalByDate(date)
    }

    // 이번주/이번달/이번해 일별 프로틴 섭취량 조회
    @GetMapping("/proteins/statistics/{standard}")
    fun findProteinTotalByDateStandard(@PathVariable standard: String): List<Any> {
        return proteinQueryService.findProteinTotalByDateStandard(standard)
    }

    // 프로틴 수정
    @PutMapping("/proteins/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: ProteinSaveRequest) {
        proteinCommandService.update(id, request.name, request.intake, request.intakeAt)
    }

    // 프로틴 삭제
    @DeleteMapping("/proteins/{id}")
    fun delete(@PathVariable id: Long) {
        proteinCommandService.delete(id)
    }
}