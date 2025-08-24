package com.yoojin.peuteu.api.target.infrastructure

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface ProteinTargetMyBatisRepository {
    @Select("""
        SELECT pt.target
        FROM protein_target pt
        WHERE pt.user_id = #{userId}
        AND pt.created_at = (
            SELECT MAX(ptt.created_at)
                FROM protein_target ptt
                WHERE ptt.user_id = #{userId}
        )
        LIMIT 1
    """)
    fun findProteinTarget(userId: Long): Double
}