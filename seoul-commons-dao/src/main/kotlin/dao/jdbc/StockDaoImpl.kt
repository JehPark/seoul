package dao.jdbc

import dao.StockDao
import model.entity.StockEntity
import model.entity.UserEntity
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
            "${StockEntity.COLUMN_LASTDAY_PRICE} " +
            "from stocks " +
            "where id = ?"
        return jdbcTemplate.queryForObject(findByIdSql, mapper, stockId)
    }

    override fun findByTicker(ticker: String): StockEntity? {
        val findByTickerSql = "select ${StockEntity.COLUMN_ID}, " +
            "${StockEntity.COLUMN_FULLNAME}, " +
            "${StockEntity.COLUMN_TICKER}, " +
            "${StockEntity.COLUMN_LASTDAY_PRICE} " +
            "from stocks" +
            "where ticker = ?"
        return jdbcTemplate.queryForObject(findByTickerSql, mapper, ticker)
    }

    override fun delete(stockId: Long): Int {
        val deleteQuery = "delete from stocks where id = ?"
        return jdbcTemplate.update(deleteQuery, stockId)
    }

    override fun save(entity: StockEntity): Int {
        val saveQuery = "insert into stocks (" +
            "${StockEntity.COLUMN_FULLNAME}, " +
            "${StockEntity.COLUMN_TICKER}, " +
            "${StockEntity.COLUMN_LASTDAY_PRICE}) " +
            "values(?, ?, ?)"
        return jdbcTemplate.update(saveQuery, entity.fullname, entity.ticker, entity.lastDayPrice)
    }

    override fun update(entity: StockEntity): Int {
        val updateQuery = "update stocks set " +
            "${StockEntity.COLUMN_FULLNAME}=?, " +
            "${StockEntity.COLUMN_TICKER}=?, " +
            "${StockEntity.COLUMN_LASTDAY_PRICE}=?, " +
            "where id=?"
        return jdbcTemplate.update(
            updateQuery,
            entity.fullname,
            entity.ticker,
            entity.lastDayPrice,
            entity.id
        )
    }
}