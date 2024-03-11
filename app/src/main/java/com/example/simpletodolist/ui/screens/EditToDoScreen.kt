package com.example.simpletodolist.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.simpletodolist.R
import com.example.simpletodolist.model.MainViewModel
import com.example.simpletodolist.model.ToDoItem


@Composable
fun EditToDoScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {

    val todo = viewModel.currentToDo

    var title by remember { mutableStateOf(todo.title) }
    var subject by remember { mutableStateOf(todo.subject) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(stringResource(id = R.string.enterTitleLabel)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text(stringResource(id = R.string.enterSubjectLabel)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f) // Make the button take equal space
            ) {
                Text(stringResource(id = R.string.dismissButton))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    viewModel.addToList(ToDoItem(id = todo.id, title = title, subject = subject))
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f) // Make the button take equal space
            ) {
                Text(stringResource(id = R.string.confirmButton))
            }
        }
    }
}
