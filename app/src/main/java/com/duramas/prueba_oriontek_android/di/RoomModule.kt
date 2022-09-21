package com.duramas.prueba_oriontek_android.di

import android.content.Context
import androidx.room.Room
import com.duramas.prueba_oriontek_android.data.local.ClienteDAO
import com.duramas.prueba_oriontek_android.data.local.ClienteDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun ProvideClienteDb(@ApplicationContext context: Context): ClienteDb {
        return Room.databaseBuilder(
            context,
            ClienteDb::class.java,
            ClienteDb.DATABASE_NAME        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun ProvideClienteDAO(clienteDb: ClienteDb): ClienteDAO {
        return clienteDb.clienteDAO()
    }

}