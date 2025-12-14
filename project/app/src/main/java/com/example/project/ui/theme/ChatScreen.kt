package com.example.project.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(modifier: Modifier = Modifier, viewModel: ChatUIViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Column(modifier, verticalArrangement = Arrangement.SpaceBetween) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            itemsIndexed(viewModel.chatList) { index: Int, item: ChatItem ->
                ChatUIItem(modifier = Modifier.fillMaxWidth(), item)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = viewModel.message,
                onValueChange = viewModel::onMessageChanged
            )

            TextButton(onClick = viewModel::chatGemini) {
                Text("Message'i gonder")
            }
        }

    }
}