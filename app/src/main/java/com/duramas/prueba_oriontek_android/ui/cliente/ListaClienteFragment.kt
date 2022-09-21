package com.duramas.prueba_oriontek_android.ui.cliente


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.ListaClienteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListaClienteFragment : Fragment() {

    private lateinit var viewModel: ListaClienteViewModel
    private lateinit var binding: ListaClienteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListaClienteFragmentBinding.inflate(inflater)

        binding.floatingActionButtonAgregarCliente.setOnClickListener({
            findNavController().navigate(R.id.action_listaClienteFragment_to_registroClienteFragment)
        })
        return binding.root
    }
}