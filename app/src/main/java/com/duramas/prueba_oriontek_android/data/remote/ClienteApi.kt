package com.duramas.prueba_oriontek_android.data.remote

import com.duramas.prueba_oriontek_android.models.Cliente
import com.duramas.prueba_oriontek_android.models.Direccion
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClienteApi {
    @GET("api/Cliente")
    suspend fun getCliente(): List<Cliente>

    @GET("api/Direccion/{clienteId}")
    suspend fun getDirecciones(@Path("clienteId")clienteId :Int): MutableList<Direccion>

    @POST("api/Cliente")
    suspend fun postCliente(@Body cliente: Cliente?): Response<Cliente>

    @POST("api/Direccion")
    suspend fun postDirecciones(@Body direcciones: List<Direccion>): Response<List<Direccion>>
}