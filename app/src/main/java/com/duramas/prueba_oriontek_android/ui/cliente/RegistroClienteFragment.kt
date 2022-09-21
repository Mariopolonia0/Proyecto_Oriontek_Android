package com.duramas.prueba_oriontek_android.ui.cliente


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.RegistroClienteFragmentBinding
import com.duramas.prueba_oriontek_android.models.Direccion
import com.duramas.prueba_oriontek_android.ui.direccion.AgregarDireccion
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistroClienteFragment : Fragment() {

    val viewModel : RegistroClienteViewModel by viewModels()
    private lateinit var binding: RegistroClienteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = RegistroClienteFragmentBinding.inflate(inflater)

        binding.floatingActionButtonAgregarDireccion.setOnClickListener({
            AgregarDireccion(this).show(requireActivity().supportFragmentManager,"MY_BOTTOM_SHEET")
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

    fun AddDireccion(direccion: Direccion){
        viewModel.listaDirecciones.add(direccion)
        //actualizar lista de direcciones
        binding.recyclerView.adapter = AdacterDireccion()
        val adapter = binding.recyclerView.adapter as AdacterDireccion
        adapter.submitList(viewModel.listaDirecciones)
    }

}