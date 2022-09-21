package com.duramas.prueba_oriontek_android.data.repository

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

    suspend fun getClientes():Flow<List<Cliente>> = flow {
        try {
            val clientes = clienteDAO.getClientes()
            emit(clientes)
        }catch (exception:Exception){

        }
    }
}