package com.stockalert.tokyo.infra.mail

class MailManagerImpl: MailManager {
    override fun send(email: String, nickname: String, title: String, text: String) {
        println(email)
    }
}