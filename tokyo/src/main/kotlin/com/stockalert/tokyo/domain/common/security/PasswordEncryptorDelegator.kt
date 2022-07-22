package com.stockalert.tokyo.domain.common.security

class PasswordEncryptorDelegator: PasswordEncryptor {

    override fun encrypt(password: String): String {
        return password
    }
}