package com.yoojin.peuteu.api.protein.presentation

import com.yoojin.peuteu.api.protein.application.ProteinCommandService
import com.yoojin.peuteu.api.protein.application.ProteinQueryService
import com.yoojin.peuteu.api.protein.presentation.dto.request.ProteinRequest
import com.yoojin.peuteu.api.protein.presentation.dto.response.ProteinResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RequestMapping("/api")
@RestController
class ProteinApiController(
    private val proteinCommandService: ProteinCommandService,
    private val proteinQueryService: ProteinQueryService
) {

    @PostMapping("/proteins")
    fun create(@RequestBody request: ProteinRequest) {
        proteinCommandService.save(request.name, request.amount)
    }

    @GetMapping("/proteins/{date}")
    fun findAllByDate(@PathVariable date: String, @PageableDefault(size = 10, sort = ["createdAt"], direction = Sort.Direction.DESC) pageable: Pageable): Page<ProteinResponse> {
        return proteinQueryService.findAllByDate(date, pageable);
    }

    @GetMapping("/proteins/total/{date}")
    fun findProteinTotalByDate(@PathVariable date: String): Double {
        return proteinQueryService.findProteinTotalByDate(date);
    }

    @PutMapping("/proteins/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: ProteinRequest) {
        proteinCommandService.update(id, request.name, request.amount)
    }

    @DeleteMapping("/proteins/{id}")
    fun delete(@PathVariable id: Long) {
        proteinCommandService.delete(id)
    }
}