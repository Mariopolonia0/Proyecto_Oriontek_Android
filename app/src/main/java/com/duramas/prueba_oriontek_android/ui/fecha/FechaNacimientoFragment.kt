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
        return binding.datePicker.dayOfMonth.toString() + "/" +
                binding.datePicker.month.toString() + "/" +
                binding.datePicker.year
    }

}