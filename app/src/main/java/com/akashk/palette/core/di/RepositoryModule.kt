package com.akashk.palette.core.di

import com.akashk.palette.domain.data.DemoPaletteRepository
import com.akashk.palette.domain.data.PaletteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPaletteRepository(repo: DemoPaletteRepository): PaletteRepository
}
