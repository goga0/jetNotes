package com.r4men.notes.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun DetailsDropdownMenu(
    expanded: Boolean
){

    var expanded by remember { mutableStateOf(expanded) }

    var selectedOption by remember { mutableStateOf("") }

}