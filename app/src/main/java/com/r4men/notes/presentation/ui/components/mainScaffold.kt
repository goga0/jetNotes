package com.r4men.notes.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.r4men.notes.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreenScaffold(
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    content: @Composable (PaddingValues) -> Unit = {},
) {
    ModalNavigationDrawer(
        drawerState = drawerState ,
        drawerContent = {
            ModalDrawerSheet {
                Text("")
                NavigationDrawerItem(
                    label = {},
                    onClick = {},
                    selected = false
                )
            }
        }
    ){
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    title = {
                        Row {
                            Icon(
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .clickable {
                                        scope.launch {
                                            drawerState.apply {
                                                if (isOpen) open() else close()
                                            }
                                        }
                                    },
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = stringResource(R.string.NavigationDrawerDescription)
                            )
                            Text(
                                modifier = Modifier.padding(start = 7.dp),
                                text = stringResource(R.string.app_name)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.padding(bottom = 20.dp, end = 20.dp),
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        ) { innerPadding ->
            content(innerPadding)
        }
    }
}