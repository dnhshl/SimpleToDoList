package com.example.simpletodolist.model

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID

val Context.dataStore by preferencesDataStore(name = "todo_list")

@Serializable
data class ToDoItem(
    val id: String = UUID.randomUUID().toString(), // a unique random string
    val title: String = "",       // title of the to do item
    val subject: String = ""      // subject of the to do item
)


data class SnackbarState(
    val message: String,
    val duration: SnackbarDuration = SnackbarDuration.Short
)


class MainViewModel(application: Application): AndroidViewModel(application) {


    // Datastore operations
    // ---------------------------------------------------------------------
    private val dataStore = application.dataStore
    private val toDoListKey = stringPreferencesKey("todo_list_key")

    val toDoList = dataStore.data
        .map { preferences ->
            val toDoListAsString = preferences[toDoListKey] ?: "[]"
            Log.i(">>>datastore", "$toDoListAsString")
            Json.decodeFromString<List<ToDoItem>>(toDoListAsString)
        }

    // Ein neues todo hinzufügen oder ein bestehendes todo ersetzen
    fun addToList(todo: ToDoItem) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                // aktualle Liste aus Datastore lesen (kodiert als String)
                val currentListAsString = preferences[toDoListKey] ?: "[]"
                // aktuelle Liste dekodieren
                val currentList = Json.decodeFromString<MutableList<ToDoItem>>(currentListAsString)
                // todo hinzufügen oder ersetzen
                val index = currentList.indexOfFirst { it.id == todo.id }
                if (index == -1) {
                    // If the item is not present, add it to the list
                    currentList.add(todo)
                } else {
                    // If the item is present, replace it with the new ToDoItem
                    currentList[index] = todo
                }
                // neue Liste kodieren und in Datastore schreiben
                preferences[toDoListKey] = Json.encodeToString<List<ToDoItem>>(currentList)
            }
        }
    }

    // Ein todo aus der Liste entfernen basierend auf der eindeutigen id
    fun deleteFromList(id: String) {
        Log.i(">>>>","DeleteFromList Called $id")
        viewModelScope.launch {
            dataStore.edit { preferences ->
                // aktuelle Liste lesen kodiert als String
                val currentListAsString = preferences[toDoListKey] ?: "[]"
                // aktuelle Liste dekodieren
                val currentList = Json.decodeFromString<MutableList<ToDoItem>>(currentListAsString)
                // neue Liste generieren, zu löschendes Element ausfiltern
                val updatedList = currentList.filterNot { it.id == id }
                // gefilterte Liste kodieren und in datastore schreiben
                preferences[toDoListKey] = Json.encodeToString<List<ToDoItem>>(updatedList)
            }
        }
    }


    // Merker des aktuell angeklickten todos
    private var _currentToDo = ToDoItem()
    val currentToDo: ToDoItem get() = _currentToDo

    fun setCurrentToDo(todo: ToDoItem) {
        _currentToDo = todo
        Log.i(">>>", "currentToDo $_currentToDo" )
    }


    // Snackbar
    // ---------------------------------------------------------------------

    private val _snackbarState = MutableStateFlow<SnackbarState?>(null)
    val snackbarState: StateFlow<SnackbarState?> = _snackbarState.asStateFlow()

    fun showSnackbar(
        message: String,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        _snackbarState.value = SnackbarState(message, duration)
    }

    fun dismissSnackbar() {
        Log.i(">>>", "Snackbar dismissed")
        _snackbarState.value = null
    }
}