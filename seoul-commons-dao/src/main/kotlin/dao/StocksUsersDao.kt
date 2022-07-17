package dao

interface StocksUsersDao {
    fun findStockIdsByUserId(userId: Long): List<Long>
    fun delete(userId: Long, stockId: Long): Int
    fun save(userId: Long, stockId: Long): Int
}