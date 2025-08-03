package com.yoojin.peuteu.api.target.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "protein_target")
class ProteinTarget (

    var userId: Long = 1,

    var target: Double,

    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    companion object {
        fun create(userId: Long, target: Double): ProteinTarget {
            return ProteinTarget(userId, target)
        }
    }
}