package com.r4men.notes.di

import android.content.Context
import androidx.room.Room
import com.r4men.notes.data.NoteDao
import com.r4men.notes.data.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDatabase( @ApplicationContext context: Context): NoteDatabase{
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notesDatabase.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao{
        return noteDatabase.getNoteDao()
    }
}