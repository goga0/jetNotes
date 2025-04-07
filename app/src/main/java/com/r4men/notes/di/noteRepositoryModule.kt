package com.r4men.notes.di

import com.r4men.notes.data.NoteDao
import com.r4men.notes.domain.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn
object noteRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNoteRepository(
        impl: NoteRepositoryImpl
    ): NoteRepository

    @Provides
    suspend fun noteRepositoryProvider(noteDao: NoteDao): NoteRepository{
        return NoteRepository(noteDao)
    }
}