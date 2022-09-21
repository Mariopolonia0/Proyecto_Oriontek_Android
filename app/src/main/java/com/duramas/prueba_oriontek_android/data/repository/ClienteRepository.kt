package com.duramas.prueba_oriontek_android.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.duramas.prueba_oriontek_android.data.local.ClienteDAO
import com.duramas.prueba_oriontek_android.models.Cliente
import com.duramas.prueba_oriontek_android.models.Direccion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val clienteDAO: ClienteDAO
) {
    suspend fun insertarCliente(cliente: Cliente) =
        clienteDAO.insertarCliente(cliente)

    suspend fun octenerUltimoRegistroCliente(): Int = clienteDAO.getultimoCliente()

    suspend fun insertarDireccionesCliente(direcciones: List<Direccion>) =
        clienteDAO.insertarDirecciones(direcciones)

    suspend fun GetDirecciones(clienteId: Int): List<Direccion> {
        try {
            return clienteDAO.getDireccioneClientes(clienteId)
        } catch (exception: Exception) {
            Log.e("GetDirecciones", exception.toString())
        }
        return ArrayList<Direccion>()
    }

    suspend fun getClientes():Flow<List<Cliente>> = flow {
        try {
            emit(clienteDAO.getClientes())
        }catch (exception:Exception){
            Log.e("GetClientesRepository",exception.toString())
        }
    }
}