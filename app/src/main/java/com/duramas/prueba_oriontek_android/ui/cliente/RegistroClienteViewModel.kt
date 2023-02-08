package com.duramas.prueba_oriontek_android.ui.cliente

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duramas.prueba_oriontek_android.common.DataState
import com.duramas.prueba_oriontek_android.data.repository.ClienteRepository
import com.duramas.prueba_oriontek_android.models.Cliente
import com.duramas.prueba_oriontek_android.models.Direccion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroClienteViewModel @Inject constructor(
    private val repository: ClienteRepository
) : ViewModel() {

    var clienteId: Int = 0

    var listaDirecciones = MutableLiveData<MutableList<Direccion>>()

    fun GuardarRemoto(cliente: Cliente) = viewModelScope.launch(Dispatchers.IO) {
        repository.enviarClienteApi(cliente, listaDirecciones.value!!.toList())
    }

    fun Guardar(cliente: Cliente, list: ArrayList<Direccion>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertarCliente(cliente)
            if (cliente.clienteId == 0)
                cliente.clienteId = repository.octenerUltimoRegistroCliente()
            list.map { it.clienteId = cliente.clienteId }
            repository.insertarDireccionesCliente(list)
        }

    fun agregarDireccion(direccion: Direccion){
        if(listaDirecciones.value == null){
            listaDirecciones.value = mutableListOf()
            listaDirecciones.value!!.add(direccion)
        }else
            listaDirecciones.value!!.add(direccion)
    }

    fun GetDireccion() = viewModelScope.launch(Dispatchers.IO) {

        repository.getDireccionRemote(clienteId).onEach { result ->
            when (result) {
                is DataState.Loading -> {

                }

                is DataState.Success -> {
                    listaDirecciones.value = result.data!!
                }

                is DataState.Error -> {
                    // _errorMessage.value = result.message ?: "ocurrio un error desconocido"
                }
            }
        }.launchIn(viewModelScope)
    }
}