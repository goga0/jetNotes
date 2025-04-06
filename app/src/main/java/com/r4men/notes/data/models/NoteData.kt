package com.r4men.notes.data.models

import android.graphics.Bitmap

class NoteData(
    val text: String? = null,
    val image: Bitmap? = null,
    val audioData: List<String>? = null
)