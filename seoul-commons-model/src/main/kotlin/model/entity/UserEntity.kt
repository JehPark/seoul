package model.entity

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

@Entity
@Table(name = "user")
class UserEntity(
    @Id
    @GeneratedValue
    @Column(name = COLUMN_ID, nullable = false, insertable = false, updatable = false)
    var id: Long,
    @Column(name = COLUMN_EMAIL, nullable = false, length = 50, unique = true)
    var email: String,
    @Column(name = COLUMN_NICKNAME, nullable = false, length = 100, unique = true)
    var nickname: String,
    @Temporal(TemporalType.DATE)
    @Column(name = COLUMN_SIGNED_UP_AT, nullable = false)
    var signedUpAt: Date,
    @Column(name = COLUMN_PASSWORD, nullable = false, length = 30)
    var password: String
) {
    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_SIGNED_UP_AT = "signed_up_at"
        const val COLUMN_NICKNAME = "nickname"
        const val COLUMN_PASSWORD = "password"
    }
}