package com.duramas.prueba_oriontek_android.ui.cliente

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duramas.prueba_oriontek_android.common.DataState
import com.duramas.prueba_oriontek_android.data.repository.ClienteRepository
import com.duramas.prueba_oriontek_android.models.Cliente
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaClienteViewModel @Inject constructor(
    private val repository: ClienteRepository
) : ViewModel() {

    private val _clientes = MutableLiveData<List<Cliente>>()
    val clientes: LiveData<List<Cliente>> get() = _clientes

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        actualizarLista()
    }

    fun actualizarLista(){
        repository.getClienteRemote().onEach { result ->
            when (result) {
                is DataState.Loading -> {

                }

                is DataState.Success -> {
                    _clientes.value = result.data!!
                }

                is DataState.Error -> {
                    _errorMessage.value = result.message ?: "ocurrio un error desconocido"
                }
            }
        }.launchIn(viewModelScope)
    }

    // val listaCliente = repository.getClientes()

//    fun deleteCliente(cliente: Cliente) = viewModelScope.launch(Dispatchers.IO) {
//        repository.deleteClientes(cliente)
//    }
}