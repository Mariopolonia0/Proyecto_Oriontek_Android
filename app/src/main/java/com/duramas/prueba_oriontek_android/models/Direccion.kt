package com.duramas.prueba_oriontek_android.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Direccion(
    @PrimaryKey(autoGenerate = true)
    var DireccionId : Int,
    var ClienteId : Int,
    var Estado : String,
    var Ciudad : String,
    var Sector : String,
    var Calle: String,
    var NumeroCasa : Int
)
