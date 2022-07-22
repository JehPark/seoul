package com.stockalert.tokyo.infra.repository

import dao.UserDao
import model.entity.UserEntity
import java.util.Date

class UserRegister(
    val userDao: UserDao
) {
    fun register(nickname: String, email: String, password: String, signedUpAt: Date): UserEntity {
        return userDao.save(nickname, password, email, signedUpAt)
    }
}