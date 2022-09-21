package com.duramas.prueba_oriontek_android.ui.direccion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.AgregarDireccionFragmentBinding
import com.duramas.prueba_oriontek_android.databinding.RegistroClienteFragmentBinding
import com.duramas.prueba_oriontek_android.ui.cliente.RegistroClienteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AgregarDireccion : BottomSheetDialogFragment() {

    private lateinit var viewModel: RegistroClienteViewModel
    private lateinit var binding: AgregarDireccionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AgregarDireccionFragmentBinding.inflate(inflater)


        return binding.root
    }

}