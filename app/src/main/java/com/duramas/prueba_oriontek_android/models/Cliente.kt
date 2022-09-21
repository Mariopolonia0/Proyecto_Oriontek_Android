package com.duramas.prueba_oriontek_android.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    var ClienteId: Int,
    var Nombre: String,
    var Apellido: String,
    var FechaNacimiento: String,
    var numeroTelefono: String,
    var nacionalidad: String
)
