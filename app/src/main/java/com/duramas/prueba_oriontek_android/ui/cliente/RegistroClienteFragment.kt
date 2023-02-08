package com.duramas.prueba_oriontek_android.ui.cliente

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.RegistroClienteFragmentBinding
import com.duramas.prueba_oriontek_android.models.Cliente
import com.duramas.prueba_oriontek_android.models.Direccion
import com.duramas.prueba_oriontek_android.ui.direccion.AgregarDireccion
import com.duramas.prueba_oriontek_android.ui.fecha.FechaNacimientoFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistroClienteFragment : Fragment() {

    val viewModel: RegistroClienteViewModel by viewModels()
    private lateinit var binding: RegistroClienteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = RegistroClienteFragmentBinding.inflate(inflater)

        binding.floatingActionButtonAgregarDireccion.setOnClickListener({
            AgregarDireccion(this).show(requireActivity().supportFragmentManager, "MY_BOTTOM_SHEET")
        })

        binding.floatingActionButtonAgregarFechaNacimientoRegistro.setOnClickListener({
            FechaNacimientoFragment(this).show(
                requireActivity().supportFragmentManager,
                "MY_BOTTOM_SHEET"
            )
        })

        viewModel.listaDirecciones.observe(this.viewLifecycleOwner){
            val adacterDireccion = AdacterDireccion()
            adacterDireccion.submitList(it.toList())
            binding.recyclerView.adapter = adacterDireccion
        }

        LlenarVista()

        return binding.root
    }

    private fun Guardar() {
        try {
            if (!Validar())
                return
            viewModel.GuardarRemoto(GetCliente())
            LimpiarVista()
            Snackbar.make(binding.root, "Cliente Guardado", Snackbar.LENGTH_LONG).show()
        } catch (exeption: Exception) {
            Snackbar.make(binding.root, exeption.toString(), Snackbar.LENGTH_LONG).show()
        }
    }

    //SE CARGAN TODOS LOS DATO QUE SE RECIBE DEL CLIENTE CUANDO SE SELECIONA UN CLIENTE
    private fun LlenarVista() {
        if (arguments != null) {
            viewModel.clienteId = arguments?.getInt("clienteId")!!
            viewModel.GetDireccion()
            binding.nombreTextEdit.setText(arguments?.getString("Nombre")!!)
            binding.ApellidoTextEdit.setText(arguments?.getString("Apellido")!!)
            binding.textViewFechaNacimiento.setText(arguments?.getString("FechaNacimiento")!!)
            binding.numeroTelefonoEditText.setText(arguments?.getString("numeroTelefono")!!)
            binding.nacionalidadEditText.setText(arguments?.getString("nacionalidad")!!)
           // CargarListaDireccione()
        }
    }

    private fun LimpiarVista() {
        binding.nombreTextEdit.setText("")
        binding.ApellidoTextEdit.setText("")
        binding.textViewFechaNacimiento.setText("")
        binding.numeroTelefonoEditText.setText("")
        binding.nacionalidadEditText.setText("")
        viewModel.clienteId = 0
        viewModel.listaDirecciones.value!!.clear()
        CargarListaDireccione()
    }

    private fun Validar(): Boolean {

        if (binding.nombreTextEdit.text.toString().length == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        if (binding.ApellidoTextEdit.text.toString().length == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        if (binding.textViewFechaNacimiento.text.toString().length == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        if (binding.numeroTelefonoEditText.text.toString().length == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        if (binding.nacionalidadEditText.text.toString().length == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        if ( viewModel.listaDirecciones.value!!.count() == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun GetCliente(): Cliente {
        return Cliente(
            viewModel.clienteId,
            binding.nombreTextEdit.text.toString(),
            binding.ApellidoTextEdit.text.toString(),
            binding.textViewFechaNacimiento.text.toString(),
            binding.numeroTelefonoEditText.text.toString(),
            binding.nacionalidadEditText.text.toString()
        )
    }

    //llenar el reclecyview
    private fun CargarListaDireccione() {
        val adacterDireccion = AdacterDireccion()
        adacterDireccion.submitList(viewModel.listaDirecciones.value!!.toList())
        binding.recyclerView.adapter = adacterDireccion
    }

    fun AddFechaNacimiento(fecha: String) {
        binding.textViewFechaNacimiento.setText(fecha)
    }

    fun AddDireccion(direccion: Direccion) {
        viewModel.agregarDireccion(direccion)
        CargarListaDireccione()
        //actualizar lista de direcciones
    }

    //agregar el boton a el menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    //funcion para realizar el evento del menu de la bara pricipal
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.guardar -> {
                Guardar()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}