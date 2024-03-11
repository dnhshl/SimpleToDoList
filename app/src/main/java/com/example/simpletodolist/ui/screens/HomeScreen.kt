package com.example.simpletodolist.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.simpletodolist.R
import com.example.simpletodolist.model.MainViewModel
import com.example.simpletodolist.model.ToDoItem
import com.example.simpletodolist.model.randomSubject
import com.example.simpletodolist.model.randomTitle
import com.example.simpletodolist.ui.navigation.NavDestination
import java.util.UUID
import kotlin.random.Random


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    val toDoList by viewModel.toDoList.collectAsState(initial = emptyList<ToDoItem>())

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.i(">>>>", "$toDoList")
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {

            items(toDoList) {todo ->
                ListItemCard(
                    todo = todo,
                    isSelected = false,
                    onItemClick = { },
                    onDeleteClick = { viewModel.deleteFromList(todo.id) },
                    onEditClick = {
                        viewModel.setCurrentToDo(todo)
                        navController.navigate(NavDestination.EditToDo.route)
                    }
                )
            }
        }
/*        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                val newToDoItem = ToDoItem(title = randomTitle(), subject = randomSubject())
                viewModel.addToList(newToDoItem)
                //viewModel.showSnackbar("$newToDoItem", duration = SnackbarDuration.Indefinite)
            }) {
                Text("Add a To Do")
            }
        }*/
    }
}


// Composable for a single list item
@Composable
fun ListItemCard(
    todo: ToDoItem,
    isSelected: Boolean = false,
    onItemClick: () -> Unit,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    // Menu State
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(onClick = onItemClick),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick() }
                .background(if (isSelected) MaterialTheme.colorScheme.tertiaryContainer else Color.Transparent)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = todo.title, style = MaterialTheme.typography.bodyLarge)
                Text(text = todo.subject, style = MaterialTheme.typography.bodySmall)
            }

            // Dropdown Menu Button
            Box { // To position the DropdownMenu correctly
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "More Options")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Row {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(stringResource(id = R.string.deleteLabel))
                            }
                        },
                        onClick = {
                            expanded = false
                            onDeleteClick()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Row {
                                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(stringResource(id = R.string.editLabel))
                            }
                        },
                        onClick = {
                            expanded = false
                            onEditClick()
                        }
                    )
                }
            }

        }
    }
}





