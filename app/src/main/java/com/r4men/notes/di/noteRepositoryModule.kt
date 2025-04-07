package com.r4men.notes.di

import com.r4men.notes.data.repository.NoteRepositoryImpl
import com.r4men.notes.domain.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn
abstract class BindNoteRepository {
    @Binds
    @Singleton
    abstract fun bindNoteRepository(
        impl: NoteRepositoryImpl
    ): NoteRepository
}
