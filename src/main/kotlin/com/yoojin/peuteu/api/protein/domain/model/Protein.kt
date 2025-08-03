package com.yoojin.peuteu.api.protein.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@DynamicUpdate
@Table(name = "protein")
@Entity
class Protein(

    var name: String,

    var intake: Double,

    var intakeAt: LocalDateTime,

    var userId: Long = 1,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    var deleted: Boolean = false
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    companion object {
        fun create(name: String, intake: Double, intakeAt: LocalDateTime, userId: Long): Protein {
            return Protein(name, intake, intakeAt, userId)
        }
    }
}
