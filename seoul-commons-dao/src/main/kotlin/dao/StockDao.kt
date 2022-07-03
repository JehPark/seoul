package dao

import model.entity.StockEntity

interface StockDao {
    fun findById(stockId: Long): StockEntity?
    fun findByTicker(ticker: String): StockEntity?
    fun save(entity: StockEntity): Int
    fun update(entity: StockEntity): Int
    fun delete(stockId: Long): Int
}