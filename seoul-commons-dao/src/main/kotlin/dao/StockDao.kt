package dao

import model.entity.StockEntity

interface StockDao {
    fun findById(stockId: Long): StockEntity?
    fun findByTicker(ticker: String): StockEntity?
}