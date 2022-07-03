package dao.jdbc

import dao.StockDao
import model.entity.StockEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class StockDaoImpl(@Autowired val jdbcTemplate: JdbcTemplate) : StockDao {
    private val logger = LoggerFactory.getLogger(StockDaoImpl::class.java)

    val mapper = RowMapper<StockEntity> { resultSet, rowId ->
        StockEntity(
            resultSet.getLong(StockEntity.COLUMN_ID),
            resultSet.getString(StockEntity.COLUMN_FULLNAME),
            resultSet.getString(StockEntity.COLUMN_TICKER),
            resultSet.getDouble(StockEntity.COLUMN_LASTDAY_PRICE)
        )
    }

    override fun findById(stockId: Long): StockEntity? {
        val findByIdSql = "select ${StockEntity.COLUMN_ID}, " +
            "${StockEntity.COLUMN_FULLNAME}, " +
            "${StockEntity.COLUMN_TICKER}, " +
            "${StockEntity.COLUMN_LASTDAY_PRICE}, " +
            "where id = ?"
        return jdbcTemplate.queryForObject(findByIdSql, mapper, stockId)
    }

    override fun findByTicker(ticker: String): StockEntity? {
        val findByTickerSql = "select ${StockEntity.COLUMN_ID}, " +
            "${StockEntity.COLUMN_FULLNAME}, " +
            "${StockEntity.COLUMN_TICKER}, " +
            "${StockEntity.COLUMN_LASTDAY_PRICE}, " +
            "where ticker = ?"
        return jdbcTemplate.queryForObject(findByTickerSql, mapper, ticker)
    }
}