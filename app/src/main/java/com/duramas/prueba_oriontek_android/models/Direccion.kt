package com.duramas.prueba_oriontek_android.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Direccion(
    @PrimaryKey(autoGenerate = true)
    var direccionId : Int,
    var clienteId : Int,
    var estado : String,
    var ciudad : String,
    var sector : String,
    var calle: String,
    var numeroCasa : String
)
