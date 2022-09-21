package com.duramas.prueba_oriontek_android.ui.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duramas.prueba_oriontek_android.data.repository.ClienteRepository
import com.duramas.prueba_oriontek_android.models.Cliente
import com.duramas.prueba_oriontek_android.models.Direccion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroClienteViewModel @Inject constructor(
    private val repository: ClienteRepository
) : ViewModel() {

    var clienteId: Int = 0
    val listaDirecciones = ArrayList<Direccion>()

    fun Guardar(cliente: Cliente) = viewModelScope.launch {
        repository.insertarCliente(cliente)
        clienteId = repository.octenerUltimoRegistroCliente()
        listaDirecciones.map { it.ClienteId = clienteId }
        repository.insertarDireccionesCliente(listaDirecciones)
    }
}