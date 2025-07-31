package com.yoojin.peuteu.api.protein.infrastructure

import com.yoojin.peuteu.api.protein.domain.repository.dto.DailyProteinTotal
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import java.time.LocalDateTime

@Mapper
interface ProteinMyBatisRepository {

    @Select("""
        WITH RECURSIVE date_range AS (
            SELECT DATE(#{startDate}) AS date
            UNION ALL
            SELECT DATE_ADD(date, INTERVAL 1 DAY)
            FROM date_range
            WHERE date < DATE(#{endDate})
        )
        SELECT d.date, COALESCE(SUM(p.amount), 0) AS total 
        FROM date_range d LEFT JOIN protein p ON d.date = DATE(p.created_at) 
        GROUP BY d.date ORDER BY d.date
    """)
    fun findAmountByCreatedAtBetweenGroupByDaily(startDate: LocalDateTime, endDate: LocalDateTime): List<DailyProteinTotal>
}