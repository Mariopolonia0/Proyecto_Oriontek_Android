package com.duramas.prueba_oriontek_android.ui.cliente

import androidx.lifecycle.ViewModel
import com.duramas.prueba_oriontek_android.models.Direccion

class RegistroClienteViewModel : ViewModel() {
    var clienteId : Int = 0
    val listaDirecciones = ArrayList<Direccion>()
}