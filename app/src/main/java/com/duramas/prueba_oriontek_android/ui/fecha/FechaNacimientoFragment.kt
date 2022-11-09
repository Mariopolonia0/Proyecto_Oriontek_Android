package com.duramas.prueba_oriontek_android.ui.fecha

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.FechaNacimientoFragmentBinding
import com.duramas.prueba_oriontek_android.ui.cliente.RegistroClienteFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FechaNacimientoFragment(
    private val registroClienteFragment: RegistroClienteFragment
) : BottomSheetDialogFragment() {

    private lateinit var binding: FechaNacimientoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FechaNacimientoFragmentBinding.inflate(inflater)

        binding.floatingActionButtonAgregarFechaNacimiento.setOnClickListener({
            registroClienteFragment.AddFechaNacimiento(getFecha())
            dismiss()
        })

        return binding.root
    }

    fun getFecha(): String {
        val mes = binding.datePicker.month
        return binding.datePicker.dayOfMonth.toString() + " de " +
                getMesLetra(mes) + " del año " +
                binding.datePicker.year
    }
    // Enero	Febrero	Marzo	Abril	Mayo	Junio	Julio	Agosto	Septiembre	Octubre	Noviembre	Diciembre.

    fun getMesLetra(mes : Int) : String{
        return when (mes){
            0 -> "Enero"
            1 -> "Febrero"
            2 -> "Marzo"
            3 -> "Abril"
            4 -> "Mayo"
            5 -> "Junio"
            6 -> "Julio"
            7 -> "Agosto"
            8 -> "Septiembre"
            9 -> "Octubre"
            10 -> "Noviembre"
            11 -> "Diciembre"
            else -> ""
        }
    }

}