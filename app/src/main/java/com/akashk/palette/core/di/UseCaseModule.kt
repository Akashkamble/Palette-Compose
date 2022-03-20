package com.akashk.palette.core.di

import com.akashk.palette.colorpicker.AddColorUseCase
import com.akashk.palette.colorpicker.IAddColorUseCase
import com.akashk.palette.palettedetail.DeleteColorUseCase
import com.akashk.palette.palettedetail.IDeleteColorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindDeleteColorUseCase(useCase: DeleteColorUseCase): IDeleteColorUseCase

    @Binds
    @Singleton
    abstract fun bindAddColorUseCase(useCase: AddColorUseCase): IAddColorUseCase
}
