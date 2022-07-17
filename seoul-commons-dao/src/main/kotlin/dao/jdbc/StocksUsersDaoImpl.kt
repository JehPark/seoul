package dao.jdbc

import dao.StocksUsersDao
import model.entity.StocksUsersEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper

class StocksUsersDaoImpl(@Autowired val jdbcTemplate: JdbcTemplate) : StocksUsersDao {
    val mapper = RowMapper<Long> { resultSet, rowId ->
        resultSet.getLong(StocksUsersEntity.COLUMN_STOCK)
    }

    override fun findStockIdsByUserId(userId: Long): List<Long> {
        val findStockIdsByUserIdQuery = "select ${StocksUsersEntity.COLUMN_STOCK} " +
            "from stocksandusers " +
            "where ${StocksUsersEntity.COLUMN_USER} = ?"
        return jdbcTemplate.query(findStockIdsByUserIdQuery, mapper, userId)
    }

    override fun delete(userId: Long, stockId: Long): Int {
        val deleteQuery = "delete from stocksandusers " +
            "where ${StocksUsersEntity.COLUMN_USER} = ? " +
            "${StocksUsersEntity.COLUMN_STOCK} = ?"
        return jdbcTemplate.update(deleteQuery, userId, stockId)
    }

    override fun save(userId: Long, stockId: Long): Int {
        val saveQuery = "insert into stocksandusers (" +
            "${StocksUsersEntity.COLUMN_USER}, " +
            "${StocksUsersEntity.COLUMN_STOCK}) " +
            "values(?, ?)"
        return jdbcTemplate.update(saveQuery, userId, stockId)
    }
}