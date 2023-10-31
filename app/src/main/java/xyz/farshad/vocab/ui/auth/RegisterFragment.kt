package xyz.farshad.vocab.ui.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.data.dto.RegisterRequest
import xyz.farshad.vocab.databinding.FragmentRegisterBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.AuthViewModel
import xyz.farshad.vocab.viewmodel.CacheViewModel
import xyz.farshad.vocab.viewmodel.util.Helper

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val authViewModel: AuthViewModel by viewModel()
    private val cacheViewModel: CacheViewModel by viewModel()

    override fun setViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun businessLogic() {
        //setObserver()
        binding.signup.setOnClickListener {
            Helper.clearError(binding.nameLy, binding.usernameLy, binding.passwordLy)
            if (Helper.checkEmptyField(binding.username, binding.usernameLy) ||
                Helper.checkEmptyField(binding.password, binding.passwordLy) ||
                Helper.checkEmptyField(binding.name, binding.nameLy)
            ) {

                val name = binding.name.text.toString()
                val username = binding.username.text.toString()
                val password = binding.password.text.toString()

                binding.signup.isEnabled = false
                authViewModel.register(
                    RegisterRequest(
                        name,
                        username,
                        password
                    )
                )
            }
        }
    }

    override fun bindView() {
    }
}