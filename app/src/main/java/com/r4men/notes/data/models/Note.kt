package com.r4men.notes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val noteValue: String?
)