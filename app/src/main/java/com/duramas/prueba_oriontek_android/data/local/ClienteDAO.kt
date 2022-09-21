package com.duramas.prueba_oriontek_android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duramas.prueba_oriontek_android.models.Cliente
import com.duramas.prueba_oriontek_android.models.Direccion

@Dao
interface ClienteDAO{

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertarCliente(cliente: Cliente)

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertarDirecciones(Direcciones: List<Direccion>)

    @Query("Select * from Cliente")
    suspend fun getClientes(): List<Cliente>
}