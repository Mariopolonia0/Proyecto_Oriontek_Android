package com.duramas.prueba_oriontek_android.ui.cliente


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.ListaClienteFragmentBinding
import com.duramas.prueba_oriontek_android.models.Cliente
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListaClienteFragment : Fragment() {

    private val viewModel: ListaClienteViewModel by viewModels()
    private lateinit var binding: ListaClienteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListaClienteFragmentBinding.inflate(inflater)

        binding.clientesRecyclerView.adapter = AdacterCliente(this)
        val adapter = binding.clientesRecyclerView.adapter as AdacterCliente

        viewModel.listaCliente.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        binding.floatingActionButtonAgregarCliente.setOnClickListener({
            findNavController().navigate(R.id.action_listaClienteFragment_to_registroClienteFragment)
        })

        return binding.root
    }

    fun EliminarCliente(cliente: Cliente) {
        viewModel.deleteCliente(cliente)
    }
}