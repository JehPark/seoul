package com.stockalert.tokyo.infra.repository

import dao.UserDao
import model.entity.UserEntity

class UserFinder(
    private val userDao: UserDao
) {
    fun byEmail(email: String): UserEntity? {
        return userDao.findByEmail(email)
    }

    fun byNickname(nickname: String): UserEntity? {
        return userDao.findByNickname(nickname)
    }
}