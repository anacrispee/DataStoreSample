package com.example.datastoresample.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    myAppViewModel: MyAppViewModel = viewModel(factory = MyAppViewModel.Factory)
) {
    val savedUserName by myAppViewModel.uiState.collectAsState()

    var userInput by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hi, ${savedUserName.userName}",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(top = 32.dp)
        )
        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            modifier = Modifier
                .padding(vertical = 32.dp)
        )
        Button(
            onClick = {
                myAppViewModel.saveUserName(userInput)
            }
        ) {
            Text(text = "SAVE")
        }
    }
}