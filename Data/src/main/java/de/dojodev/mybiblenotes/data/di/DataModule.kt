package de.dojodev.mybiblenotes.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.dojodev.mybiblenotes.data.repositories.BibleDBRepository
import de.dojodev.mybiblenotes.data.repositories.BibleServiceRepository
import de.dojodev.mybiblenotes.data.repositories.DefaultAudioBibleServiceRepository
import de.dojodev.mybiblenotes.data.repositories.DefaultBibleDBRepository
import de.dojodev.mybiblenotes.data.repositories.DefaultBibleServiceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsBibleServiceRepository(
        bibleServiceRepository: DefaultBibleServiceRepository
    ): BibleServiceRepository

    @Singleton
    @Binds
    fun bindsAudioBibleServiceRepository(
        bibleServiceRepository: DefaultAudioBibleServiceRepository
    ): BibleServiceRepository

    @Singleton
    @Binds
    fun bindsBibleDbRepository(
        bibleDBRepository: DefaultBibleDBRepository
    ): BibleDBRepository
}