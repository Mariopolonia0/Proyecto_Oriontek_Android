package com.duramas.prueba_oriontek_android.ui.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.ListaClienteFragmentBinding
import com.duramas.prueba_oriontek_android.databinding.RegistroClienteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistroClienteFragment : Fragment() {


    private lateinit var viewModel: RegistroClienteViewModel
    private lateinit var binding: RegistroClienteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistroClienteFragmentBinding.inflate(inflater)
        return binding.root
    }


}