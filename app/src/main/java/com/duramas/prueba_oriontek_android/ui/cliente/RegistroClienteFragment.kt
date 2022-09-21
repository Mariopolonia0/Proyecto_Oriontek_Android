package com.duramas.prueba_oriontek_android.ui.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.ListaClienteFragmentBinding
import com.duramas.prueba_oriontek_android.databinding.RegistroClienteFragmentBinding
import com.duramas.prueba_oriontek_android.ui.direccion.AgregarDireccion
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistroClienteFragment : Fragment() {


    private lateinit var viewModel: RegistroClienteViewModel
    private lateinit var binding: RegistroClienteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = RegistroClienteFragmentBinding.inflate(inflater)

        binding.recyclerView.adapter = AdacterDireccion()
        val adacpter = binding.recyclerView.adapter as AdacterDireccion

        binding.floatingActionButtonAgregarDireccion.setOnClickListener({
            AgregarDireccion().show(requireActivity().supportFragmentManager,"MY_BOTTOM_SHEET")
            adacpter.submitList(viewModel.listaDirecciones)
        })



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.guardar-> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}