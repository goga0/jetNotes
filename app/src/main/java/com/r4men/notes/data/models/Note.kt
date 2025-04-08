package com.r4men.notes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Note(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo var lastSaveDate: String,
    @ColumnInfo var title: String?,
    @ColumnInfo var noteValue: String?
)