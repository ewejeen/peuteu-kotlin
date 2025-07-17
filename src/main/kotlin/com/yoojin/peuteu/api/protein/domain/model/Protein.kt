package com.yoojin.peuteu.api.protein.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "protein")
@Entity
class Protein(

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var amount: Double,

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    companion object {
        fun create(name: String, amount: Double): Protein {
            return Protein(name, amount)
        }
    }
}
