package com.duramas.prueba_oriontek_android.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.duramas.prueba_oriontek_android.models.Cliente
import com.duramas.prueba_oriontek_android.models.Direccion

@Dao
interface ClienteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCliente(cliente: Cliente)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDirecciones(Direcciones: List<Direccion>)

    @Query("select Max(ClienteId) from Cliente") //Select Max(idTicket) from Tickets
    suspend fun getultimoCliente(): Int

    @Query("Select * from Cliente")
    fun getClientes(): LiveData<List<Cliente>>

    @Query("Select * from Direccion where ClienteId = :clienteId ")
    suspend fun getDireccioneClientes(clienteId: Int): List<Direccion>

    @Delete
    suspend fun eliminarCliente(cliente: Cliente)

}