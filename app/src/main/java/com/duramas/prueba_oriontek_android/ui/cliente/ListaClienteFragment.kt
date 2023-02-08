package com.duramas.prueba_oriontek_android.ui.cliente


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.ListaClienteFragmentBinding
import com.duramas.prueba_oriontek_android.models.Cliente
import com.google.android.material.snackbar.Snackbar
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

        viewModel.clientes.observe(viewLifecycleOwner, {
            adapter.submitList(it)
            binding.progressBar.isVisible = false
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            binding.progressBar.isVisible = false
            Snackbar.make(binding.root, "error cargado dato desde la web", Snackbar.LENGTH_LONG)
                .show()
        })

        binding.progressBar.isVisible = true

        binding.floatingActionButtonAgregarCliente.setOnClickListener({
            findNavController().navigate(R.id.action_listaClienteFragment_to_registroClienteFragment)
            viewModel.actualizarLista()
        })

        return binding.root
    }

    fun EliminarCliente(cliente: Cliente) {
        //viewModel.deleteCliente(cliente)
    }
}