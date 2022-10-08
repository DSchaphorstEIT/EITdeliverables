package com.example.dschaphorst_p5_dnd.di

import com.example.dschaphorst_p5_dnd.data.api.Open5eRepository
import com.example.dschaphorst_p5_dnd.data.api.Open5eRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun providesRepository(
        repositoryImpl: Open5eRepositoryImpl
    ): Open5eRepository
}