package com.stockalert.tokyo.domain.repository

import dao.UserDao
import model.entity.UserEntity
import java.util.Date

class UserRegistrator(
    val userDao: UserDao
) {
    fun register(nickname: String, email: String, password: String, signedUpAt: Date): Int {
        return userDao.save(nickname, password, email, signedUpAt)
    }
}