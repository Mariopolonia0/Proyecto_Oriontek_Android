package com.duramas.prueba_oriontek_android.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.duramas.prueba_oriontek_android.common.DataState
import com.duramas.prueba_oriontek_android.data.local.ClienteDAO
import com.duramas.prueba_oriontek_android.data.remote.ClienteApi
import com.duramas.prueba_oriontek_android.models.Cliente
import com.duramas.prueba_oriontek_android.models.Direccion
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val clienteDAO: ClienteDAO,
    private val clienteApi: ClienteApi
) {

    suspend fun enviarClienteApi(cliente: Cliente, direcciones: List<Direccion>): String {

        val callCliente = clienteApi.postCliente(cliente)
        val clienteGuardado = callCliente.body()!!

        direcciones.map{ it.clienteId = clienteGuardado.clienteId }
        val callDirecciones = clienteApi.postDirecciones(direcciones)

        return if (callCliente.isSuccessful && callDirecciones.isSuccessful) {
            "se guardo"
        } else {
            "Error al conectar con la web"
        }
    }

    fun getClienteRemote() = flow<DataState<List<Cliente>>> {
        try {
            emit(DataState.Loading())

            val clientes = clienteApi.getCliente()

            emit(DataState.Success(clientes))
        } catch (e: Exception) {
            emit(
                DataState.Error(
                    e.localizedMessage ?: "Error cargando los cliente de web"
                )
            )
        }
    }

    fun getDireccionRemote(clienteId: Int) = flow<DataState<MutableList<Direccion>>> {
        try {
            emit(DataState.Loading())

            val direcciones = clienteApi.getDirecciones(clienteId)

            emit(DataState.Success(direcciones))
        } catch (e: Exception) {
            emit(
                DataState.Error(
                    e.localizedMessage ?: "Error cargando los cliente de web"
                )
            )
        }
    }

    fun getClientes(): LiveData<List<Cliente>> = clienteDAO.getClientes()

    suspend fun deleteClientes(cliente: Cliente) = clienteDAO.eliminarCliente(cliente)

    suspend fun insertarCliente(cliente: Cliente) = clienteDAO.insertarCliente(cliente)

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
}