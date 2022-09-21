package com.duramas.prueba_oriontek_android.ui.cliente

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duramas.prueba_oriontek_android.databinding.RowDireccionBinding
import com.duramas.prueba_oriontek_android.models.Direccion

class AdacterDireccion(): RecyclerView.Adapter<AdacterDireccion.RowDireccionViewHolder>()  {

    private var direccionList = emptyList<Direccion>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowDireccionViewHolder {
        val binding = RowDireccionBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return RowDireccionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RowDireccionViewHolder, position: Int) {
        holder.bind(direccionList[position])
    }

    override fun getItemCount(): Int {
        return direccionList.size
    }

    fun submitList(list :List<Direccion>){
        direccionList = list
        notifyDataSetChanged()
    }

    inner class RowDireccionViewHolder(private val binding: RowDireccionBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(item:Direccion){
            binding.textViewCuidad.text = item.Ciudad
            binding.textViewSector.text = item.Sector
            binding.textViewCalle.text = item.Calle
            binding.textViewNumeroCasa.text = item.NumeroCasa.toString()
        }
    }
}
