package de.dojodev.mybiblenotes.bibleapi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.dojodev.mybiblenotes.bibleapi.requests.AudioBibleRequest
import de.dojodev.mybiblenotes.bibleapi.requests.BibleRequest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideAudioBibleRequest(): AudioBibleRequest {
        return AudioBibleRequest()
    }

    @Provides
    @Singleton
    fun provideBibleRequest(): BibleRequest {
        return BibleRequest()
    }
}