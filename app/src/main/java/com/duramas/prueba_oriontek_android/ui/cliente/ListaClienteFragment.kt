package com.duramas.prueba_oriontek_android.ui.cliente


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.ListaClienteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListaClienteFragment : Fragment() {

    private val viewModel: ListaClienteViewModel by viewModels()
    private lateinit var binding: ListaClienteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListaClienteFragmentBinding.inflate(inflater)

        val adapter = AdacterCliente()
        viewModel.clientes.observe(viewLifecycleOwner,{
            adapter.submitList(it)
            binding.clientesRecyclerView.adapter = adapter
        })



        binding.floatingActionButtonAgregarCliente.setOnClickListener({
            findNavController().navigate(R.id.action_listaClienteFragment_to_registroClienteFragment)
        })

        return binding.root
    }
}