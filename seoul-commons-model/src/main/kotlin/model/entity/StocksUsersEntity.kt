package model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "stocks_users")
class StocksUsersEntity(
    @Id @GeneratedValue
    @Column(name = COLUMN_ID)
    var id: Long,
    @JoinColumn(name = COLUMN_USER)
    @ManyToOne
    var user: UserEntity,
    @JoinColumn(name = COLUMN_STOCK)
    @ManyToOne
    var stock: StockEntity
) {
    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_USER = "user_id"
        const val COLUMN_STOCK = "stock_id"
    }
}