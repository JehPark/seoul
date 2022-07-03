package model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "stock")
class StockEntity(
    @Id @GeneratedValue
    @Column(name = COLUMN_ID, nullable = false, insertable = false, updatable = false)
    var id: Long,
    @Column(name = COLUMN_FULLNAME)
    var fullname: String,
    @Column(name = COLUMN_TICKER)
    var ticker: String,
    @Column(name = COLUMN_LASTDAY_PRICE)
    var lastDayPrice: Double
) {
    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_FULLNAME = "fullname"
        const val COLUMN_TICKER = "ticker"
        const val COLUMN_LASTDAY_PRICE = "last_day_price"
    }
}