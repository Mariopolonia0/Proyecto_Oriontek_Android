package com.duramas.prueba_oriontek_android.ui.cliente


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.RowClienteBinding
import com.duramas.prueba_oriontek_android.models.Cliente

//ADACTADOR PARA PRESENTAR LOS CLIENTE EN LA LISTA DE CLIENTE
class AdacterCliente() : RecyclerView.Adapter<AdacterCliente.RowClienteViewHolder>() {

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
            binding.textViewNombre.text = item.Nombre
            binding.textViewApellido.text = item.Apellido
            binding.textViewTelefono.text = item.numeroTelefono

            binding.cardView.setOnClickListener({
                val bundle = bundleOf(
                    "clienteId" to item.ClienteId,
                    "Nombre" to item.Nombre,
                    "Apellido" to item.Apellido,
                    "FechaNacimiento" to item.FechaNacimiento,
                    "numeroTelefono" to item.numeroTelefono,
                    "nacionalidad" to item.nacionalidad
                )
                binding.root.findNavController()
                    .navigate(R.id.action_listaClienteFragment_to_registroClienteFragment, bundle)
            })
        }
    }
}