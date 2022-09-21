package com.duramas.prueba_oriontek_android.di

import com.duramas.prueba_oriontek_android.data.local.ClienteDAO
import com.duramas.prueba_oriontek_android.data.repository.ClienteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideClienteRepository(clienteDAO: ClienteDAO): ClienteRepository {
        return ClienteRepository(clienteDAO)
    }
}