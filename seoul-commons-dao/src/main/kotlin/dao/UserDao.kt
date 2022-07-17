package dao

import model.entity.UserEntity
import java.util.Date

interface UserDao {
    fun findById(userId: Long): UserEntity?
    fun findAll(): List<UserEntity>
    fun findByEmail(email: String): UserEntity?
    fun findByNickname(nickname: String): UserEntity?
    fun delete(userId: Long): Int
    fun save(nickname: String, password: String, email: String, signedUpAt: Date): Int
    fun update(entity: UserEntity, columns :Collection<String>): Int
}