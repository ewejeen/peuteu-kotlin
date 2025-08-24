package com.yoojin.peuteu.api.target.infrastructure

import com.yoojin.peuteu.api.target.domain.model.ProteinTarget
import org.springframework.data.jpa.repository.JpaRepository

interface ProteinTargetJpaRepository: JpaRepository<ProteinTarget, Long> {

}