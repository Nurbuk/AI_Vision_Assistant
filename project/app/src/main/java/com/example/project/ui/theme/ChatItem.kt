package com.example.project.ui.theme

data class ChatItem(val message: String, val type: ChatType) {
}

enum class ChatType {
    AI,
    USER
}