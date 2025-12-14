package com.example.project.ui.theme

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class ChatUIViewModel : ViewModel() {

    val generativeModel = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = "AIzaSyDZeGjOIa6Nf7cAvkTzoVLXhkN4-H5cC4Q"
    )

    init {
        startChat()
    }

    var aiChat: Chat? = null

    private fun startChat() {
        aiChat = generativeModel.startChat()
    }
    val chatList = mutableStateListOf<ChatItem>()

    var message by mutableStateOf("")
        private set

    fun onMessageChanged(text: String) {
        message = text
    }

    fun addMessage() {
        chatList.add(
            ChatItem(
                message,
                if (chatList.size.mod(2) == 0) ChatType.AI else ChatType.USER
            )
        )
        message = ""
    }
    fun chatGemini() {
        if (message.isEmpty()) return
        viewModelScope.launch {
            try {
                chatList.add(ChatItem(type = ChatType.USER, message = message))
                val response = aiChat?.sendMessage(message)
                chatList.add(ChatItem(type = ChatType.AI, message = response?.text ?: "null dondu ai"))
                message = ""
            }catch (ex:Exception){
                Log.d("gemini","error : ${ex.localizedMessage}")
            }

        }
    }
}
