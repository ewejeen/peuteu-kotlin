package com.yoojin.peuteu.api.protein.infrastructure

import com.yoojin.peuteu.api.protein.domain.repository.dto.DailyProteinTotal
import com.yoojin.peuteu.api.protein.domain.repository.dto.MonthlyProteinTotal
import com.yoojin.peuteu.api.protein.domain.repository.dto.ProteinSuccessfulDates
import com.yoojin.peuteu.api.protein.domain.repository.dto.WeeklyProteinTotal
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import java.time.LocalDate

@Mapper
interface ProteinMyBatisRepository {
    @Select("""
        SELECT sub.intake_date AS date, sub.total_intake AS intake,
               (SELECT pt.target
                FROM protein_target pt
                WHERE pt.user_id = sub.user_id
                  AND pt.created_at = (SELECT MAX(ptt.created_at) 
                                         FROM protein_target ptt 
                                        WHERE ptt.user_id = sub.user_id
                                          AND DATE(ptt.created_at) <= sub.intake_date)
                LIMIT 1) AS target
        FROM (
            SELECT p.user_id,
                   DATE(p.intake_at) AS intake_date,
                   SUM(p.intake)     AS total_intake
            FROM protein p
            WHERE p.user_id = 1
              AND p.deleted = false
              AND YEAR(p.intake_at) = #{year} AND MONTH(p.intake_at) = #{month} 
            GROUP BY p.user_id, DATE(p.intake_at)
        ) AS sub
      ORDER BY date
    """)
    fun findSuccessfulDatesInMonth(year: Int, month: Int): List<ProteinSuccessfulDates>

    @Select("""
        WITH RECURSIVE date_range AS (
            SELECT DATE(#{startDate}) AS date
            UNION ALL
            SELECT DATE_ADD(date, INTERVAL 1 DAY)
            FROM date_range
            WHERE date < DATE(#{endDate})
        )
        SELECT d.date, COALESCE(SUM(p.intake), 0) AS total 
        FROM date_range d LEFT JOIN protein p ON d.date = DATE(p.intake_at)
        GROUP BY d.date ORDER BY d.date
    """)
    fun findIntakeByIntakeAtBetweenGroupByDaily(startDate: LocalDate, endDate: LocalDate): List<DailyProteinTotal>

    @Select("""
        WITH RECURSIVE date_range AS (
            SELECT
                DATE(#{startDate}) AS startDate,
                DATE_ADD(DATE(#{startDate}), INTERVAL 6 DAY) AS endDate
            UNION ALL
            SELECT
                DATE_ADD(startDate, INTERVAL 1 WEEK),
                DATE_ADD(endDate, INTERVAL 1 WEEK)
            FROM date_range
            WHERE endDate < DATE(#{endDate})
        )
        SELECT d.startDate, d.endDate, COALESCE(SUM(p.intake), 0) AS total
        FROM date_range d LEFT JOIN protein p ON DATE(p.intake_at) BETWEEN d.startDate AND d.endDate
        GROUP BY d.startDate, d.endDate ORDER BY d.startDate
    """)
    fun findIntakeByIntakeAtBetweenGroupByWeekly(startDate: LocalDate, endDate: LocalDate): List<WeeklyProteinTotal>

    @Select("""
        WITH RECURSIVE date_range AS (
            SELECT DATE(CONCAT(#{year},'-01-01')) AS month
            UNION ALL
            SELECT
                DATE_ADD(month, INTERVAL 1 MONTH)
            FROM date_range
            WHERE month < DATE(CONCAT(#{year},'-12-01'))
        )
        SELECT MONTH(d.month), COALESCE(SUM(p.intake), 0) AS total
        FROM date_range d LEFT JOIN protein p ON YEAR(p.intake_at) = #{year} AND MONTH(p.intake_at) = MONTH(d.month)
        GROUP BY month ORDER BY month
    """)
    fun findIntakeByIntakeAtBetweenGroupByMonthly(year: Int): List<MonthlyProteinTotal>
}