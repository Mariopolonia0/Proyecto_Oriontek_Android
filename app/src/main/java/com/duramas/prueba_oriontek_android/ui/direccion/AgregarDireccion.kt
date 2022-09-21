package com.duramas.prueba_oriontek_android.ui.direccion


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duramas.prueba_oriontek_android.R
import com.duramas.prueba_oriontek_android.databinding.AgregarDireccionFragmentBinding
import com.duramas.prueba_oriontek_android.models.Direccion
import com.duramas.prueba_oriontek_android.ui.cliente.RegistroClienteFragment
import com.duramas.prueba_oriontek_android.ui.cliente.RegistroClienteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

class AgregarDireccion(
    private val registroClienteFragment: RegistroClienteFragment
) : BottomSheetDialogFragment() {

    //private lateinit var viewModel: RegistroClienteViewModel
    private lateinit var binding: AgregarDireccionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AgregarDireccionFragmentBinding.inflate(inflater)

        binding.floatingActionButtonAddDireccion.setOnClickListener({
            if (!Validar())
                return@setOnClickListener

            registroClienteFragment.AddDireccion(GetDireccion())
            dismiss()
        })

        return binding.root
    }

    private fun GetDireccion(): Direccion {
        return Direccion(
            0,
            registroClienteFragment.viewModel.clienteId,
            binding.inputTextEstado.text.toString(),
            binding.inputTextCiudad.text.toString(),
            binding.inputTextSector.text.toString(),
            binding.inputTextCalle.text.toString(),
            binding.inputTextNumeroCasa.text.toString()
        )
    }

    private fun Validar(): Boolean {

        if (binding.inputTextEstado.text.toString().length == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        if (binding.inputTextCiudad.text.toString().length == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        if (binding.inputTextSector.text.toString().length == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        if (binding.inputTextCalle.text.toString().length == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        if (binding.inputTextNumeroCasa.text.toString().length == 0) {
            Snackbar.make(binding.root, R.string.error_dato_vacio, Snackbar.LENGTH_LONG).show()
            return false
        }
        return true
    }

}