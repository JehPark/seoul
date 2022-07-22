package com.stockalert.tokyo.domain.common.security

interface PasswordEncryptor {
    fun encrypt(password: String): String
}