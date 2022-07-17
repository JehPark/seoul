package dao.jdbc

import dao.UserDao
import model.entity.UserEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
class UserDaoImpl(@Autowired val jdbcTemplate: JdbcTemplate) : UserDao {
    private val logger = LoggerFactory.getLogger(UserDaoImpl::class.java)

    val mapper = RowMapper<UserEntity> { resultSet, rowId ->
        UserEntity(
            resultSet.getLong(UserEntity.COLUMN_ID),
            resultSet.getString(UserEntity.COLUMN_EMAIL),
            resultSet.getString(UserEntity.COLUMN_NICKNAME),
            resultSet.getTime(UserEntity.COLUMN_SIGNED_UP_AT),
            resultSet.getString(UserEntity.COLUMN_PASSWORD)
        )
    }

    override fun findAll(): List<UserEntity> {
        val findAllSql = "select ${UserEntity.COLUMN_ID}, " +
            "${UserEntity.COLUMN_EMAIL}, " +
            "${UserEntity.COLUMN_NICKNAME}, " +
            "${UserEntity.COLUMN_SIGNED_UP_AT}, " +
            "${UserEntity.COLUMN_PASSWORD} from users"
        return jdbcTemplate.query(findAllSql, mapper)
    }

    override fun findById(userId: Long): UserEntity? {
        val findByIdSql = "select ${UserEntity.COLUMN_ID}, " +
            "${UserEntity.COLUMN_EMAIL}, " +
            "${UserEntity.COLUMN_NICKNAME}, " +
            "${UserEntity.COLUMN_SIGNED_UP_AT}, " +
            "${UserEntity.COLUMN_PASSWORD} from users " +
            "where id = ?"
        return jdbcTemplate.queryForObject(findByIdSql, mapper, userId)
    }

    override fun findByEmail(email: String): UserEntity? {
        val findByEmailQuery = "select ${UserEntity.COLUMN_ID}, " +
            "${UserEntity.COLUMN_EMAIL}, " +
            "${UserEntity.COLUMN_NICKNAME}, " +
            "${UserEntity.COLUMN_SIGNED_UP_AT}, " +
            "${UserEntity.COLUMN_PASSWORD} from users " +
            "where ${UserEntity.COLUMN_EMAIL} = ?"

        val result = jdbcTemplate.query(findByEmailQuery, mapper, email)
        return if (result.size > 0) result.first() else null
    }

    override fun findByNickname(nickname: String): UserEntity? {
        val findByNickname = "select ${UserEntity.COLUMN_ID}, " +
            "${UserEntity.COLUMN_EMAIL}, " +
            "${UserEntity.COLUMN_NICKNAME}, " +
            "${UserEntity.COLUMN_SIGNED_UP_AT}, " +
            "${UserEntity.COLUMN_PASSWORD} from users " +
            "where ${UserEntity.COLUMN_NICKNAME} = ?"
        val result = jdbcTemplate.query(findByNickname, mapper, nickname)
        return if (result.size > 0) result.first() else null
    }

    override fun delete(userId: Long): Int {
        val deleteQuery = "delete from users where id = ?"
        return jdbcTemplate.update(deleteQuery, userId)
    }

    override fun save(nickname: String, password: String, email: String, signedUpAt: Date): Int {
        val saveQuery = "insert into users (" +
            "${UserEntity.COLUMN_NICKNAME}, " +
            "${UserEntity.COLUMN_PASSWORD}, " +
            "${UserEntity.COLUMN_EMAIL}, " +
            "${UserEntity.COLUMN_SIGNED_UP_AT}) values(?, ?, ?, ?)"
        return jdbcTemplate.update(saveQuery, nickname, password, email, signedUpAt)
    }

    override fun update(entity: UserEntity, columns: Collection<String>): Int {
        val updateQuery = "update users set " +
            "${UserEntity.COLUMN_EMAIL}=?, " +
            "${UserEntity.COLUMN_NICKNAME}=?, " +
            "${UserEntity.COLUMN_PASSWORD}=?, " +
            "${UserEntity.COLUMN_SIGNED_UP_AT}=? " +
            "where id=?"
        return jdbcTemplate.update(
            updateQuery,
            entity.email,
            entity.nickname,
            entity.password,
            entity.signedUpAt,
            entity.id
        )
    }
}