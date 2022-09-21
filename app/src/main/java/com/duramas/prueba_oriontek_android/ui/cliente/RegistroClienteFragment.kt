package com.duramas.prueba_oriontek_android.ui.cliente

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.RegistroClienteFragmentBinding
import com.duramas.prueba_oriontek_android.models.Cliente
import com.duramas.prueba_oriontek_android.models.Direccion
import com.duramas.prueba_oriontek_android.ui.direccion.AgregarDireccion
import com.duramas.prueba_oriontek_android.ui.fecha.FechaNacimientoFragment
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

        binding.floatingActionButtonAgregarFechaNacimientoRegistro.setOnClickListener({
            FechaNacimientoFragment(this).show(requireActivity().supportFragmentManager,"MY_BOTTOM_SHEET")
        })

        LlenarVista()

        return binding.root
    }
    //SE CARGAN TODOS LOS DATO QUE SE RECIBE DEL CLIENTE CUANDO SE SELECIONA UN CLIENTE
    private fun LlenarVista(){
        viewModel.clienteId = arguments?.getInt("clienteId")!!
        viewModel.GetDireccion()
        binding.nombreTextEdit.setText(arguments?.getString("Nombre")!!)
        binding.ApellidoTextEdit.setText(arguments?.getString("Apellido")!!)
        binding.fechaNacimientoEditText.setText(arguments?.getString("FechaNacimiento")!!)
        binding.numeroTelefonoEditText.setText(arguments?.getString("numeroTelefono")!!)
        binding.nacionalidadEditText.setText(arguments?.getString("nacionalidad")!!)
        CargarListaDireccione()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.guardar-> {
                Guardar()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun Guardar(){
        viewModel.Guardar(GetCliente())
    }

    private fun GetCliente(): Cliente{
        return Cliente(
            0,
            binding.nombreTextEdit.text.toString(),
            binding.ApellidoTextEdit.text.toString(),
            binding.fechaNacimientoEditText.text.toString(),
            binding.numeroTelefonoEditText.text.toString(),
            binding.nacionalidadEditText.text.toString()
        )
    }

    fun AddDireccion(direccion: Direccion){
        viewModel.listaDirecciones.add(direccion)
        //actualizar lista de direcciones
        CargarListaDireccione()
    }
    //llenar el reclecyview
    fun CargarListaDireccione(){
        binding.recyclerView.adapter = AdacterDireccion()
        val adapter = binding.recyclerView.adapter as AdacterDireccion
        adapter.submitList(viewModel.listaDirecciones)
    }

    fun AddFechaNacimiento(fecha: String){
        binding.fechaNacimientoEditText.setText(fecha)
    }

}