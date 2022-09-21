package com.duramas.prueba_oriontek_android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.duramas.prueba_oriontek_android.models.Cliente
import com.duramas.prueba_oriontek_android.models.Direccion


@Database(
    version = 1,
    entities = arrayOf(Cliente::class,Direccion::class),
    exportSchema = false
)
abstract class ClienteDb: RoomDatabase() {

    abstract fun clienteDAO(): ClienteDAO

    companion object {
        val DATABASE_NAME : String = "Clientes.db"
    }
}