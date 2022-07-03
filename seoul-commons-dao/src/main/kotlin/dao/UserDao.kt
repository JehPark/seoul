package dao

import model.entity.UserEntity

interface UserDao {
    fun findById(userId: Long): UserEntity?
    fun findAll(): List<UserEntity>
    fun delete(userId: Long): Int
    fun save(entity: UserEntity): Int
    fun update(entity: UserEntity, columns :Collection<String>): Int
}