package com.r4men.notes.presentation.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.r4men.notes.R


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DetailsScaffold(
    title: String? = "",
    onBackPressed: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit ={},
){
    var expanded by remember{ mutableStateOf(false) }
    Box{
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { onBackPressed }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                            Text(
                                text = title ?: "Заголовок",
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                expanded = true
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box{
                content(innerPadding)
                Box(
                    modifier = Modifier.padding(start = 280.dp)
                ){
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text("nigga")
                            },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = {
                                Text("nigga1")
                            },
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}