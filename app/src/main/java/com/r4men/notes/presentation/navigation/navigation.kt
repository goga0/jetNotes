package com.r4men.notes.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.r4men.notes.R
import com.r4men.notes.presentation.screens.noteDetails.NoteDetailsRoot
import com.r4men.notes.presentation.screens.noteDetails.NoteDetailsViewModel
import com.r4men.notes.presentation.screens.notesList.NotesListRoot
import com.r4men.notes.presentation.screens.notesList.NotesListViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SetupNavGraph(){

    val navController: NavHostController = rememberNavController()
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.let{
        when(it.route){
            Screens.NotesList::class.qualifiedName -> Screens.NotesList
            Screens.NoteDetails::class.qualifiedName -> Screens.NoteDetails
            Screens.Settings::class.qualifiedName -> Screens.Settings
        }
    }


    NavHost(navController, startDestination = Screens.NotesList) {

        composable<Screens.NotesList> {
            val viewModel: NotesListViewModel = hiltViewModel()
            NotesListRoot(viewModel)
        }

        composable<Screens.NoteDetails> { backStackEntry ->
            val viewModel: NoteDetailsViewModel = hiltViewModel()
            val args = backStackEntry.toRoute<Screens.NoteDetails>()
            NoteDetailsRoot(viewModel = viewModel,noteId = args.noteId)
        }

        composable<Screens.Settings> {

        }
    }
}

