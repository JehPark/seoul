package com.stockalert.tokyo.infra.mail

interface MailManager {
    fun send(email: String, nickname: String, title: String, text: String)
}