package com.duramas.prueba_oriontek_android.ui.cliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duramas.prueba_oriontek_android.data.repository.ClienteRepository
import com.duramas.prueba_oriontek_android.models.Cliente
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        getList()
    }

    fun getList() = viewModelScope.launch  {
        repository.getClientes().onEach {
            _clientes.value = it
        }.launchIn(viewModelScope)
    }

}