package com.duramas.prueba_oriontek_android.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    var clienteId: Int,
    var nombre: String,
    var apellido: String,
    var fechaNacimiento: String,
    var numeroTelefono: String,
    var nacionalidad: String
)
