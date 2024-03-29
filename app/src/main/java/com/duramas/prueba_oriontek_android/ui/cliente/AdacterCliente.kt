package com.duramas.prueba_oriontek_android.ui.cliente

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.data.repository.ClienteRepository
import com.duramas.prueba_oriontek_android.databinding.RowClienteBinding
import com.duramas.prueba_oriontek_android.models.Cliente
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//ADACTADOR PARA PRESENTAR LOS CLIENTE EN LA LISTA DE CLIENTE
//le pado el fragmento para usar la funcion eliminar del fragmento
class AdacterCliente (
    val fragment: ListaClienteFragment
): RecyclerView.Adapter<AdacterCliente.RowClienteViewHolder>() {

    private var clienteList = emptyList<Cliente>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowClienteViewHolder {
        val binding = RowClienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RowClienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RowClienteViewHolder, position: Int) {
        holder.bind(clienteList[position])
    }

    override fun getItemCount(): Int {
        return clienteList.size
    }

    fun submitList(list: List<Cliente>) {
        clienteList = list
        notifyDataSetChanged()
    }

    inner class RowClienteViewHolder(private val binding: RowClienteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cliente) {
            binding.textViewNombre.text = item.nombre
            binding.textViewApellido.text = item.apellido
            binding.textViewTelefono.text = item.numeroTelefono

            binding.cardView.setOnClickListener({
                val bundle = bundleOf(
                    "clienteId" to item.clienteId,
                    "Nombre" to item.nombre,
                    "Apellido" to item.apellido,
                    "FechaNacimiento" to item.fechaNacimiento,
                    "numeroTelefono" to item.numeroTelefono,
                    "nacionalidad" to item.nacionalidad
                )

                binding.root.findNavController()
                    .navigate(R.id.action_listaClienteFragment_to_registroClienteFragment, bundle)
            })

//            binding.floatingActionButtonEliminarCliente.setOnClickListener({
//                fragment.EliminarCliente(item)
//            })
        }
    }
}