package xyz.farshad.vocab.ui.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import io.neoattitude.defio.util.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.data.dto.LoginRequest
import xyz.farshad.vocab.data.entity.Cache
import xyz.farshad.vocab.databinding.FragmentLoginBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.AuthViewModel
import xyz.farshad.vocab.viewmodel.CacheViewModel
import xyz.farshad.vocab.viewmodel.util.Helper
import xyz.farshad.vocab.viewmodel.util.Helper.snack

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val authViewModel: AuthViewModel by viewModel()
    private val cacheViewModel: CacheViewModel by viewModel()

    override fun setViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun bindView() {
    }

    override fun businessLogic() {
        cacheViewModel.delete("token")
        setObserver()
        binding.login.setOnClickListener {
            Helper.clearError(binding.usernameLy, binding.passwordLy)
            if (Helper.checkEmptyField(binding.username, binding.usernameLy) &&
                Helper.checkEmptyField(binding.password, binding.passwordLy)
            ) {

                val username = binding.username.text.toString()
                val password = binding.password.text.toString()

                binding.login.isEnabled = false
                authViewModel.login(
                    LoginRequest(
                        username,
                        password
                    )
                )
            }
        }

        binding.register.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    private fun setObserver() {
        authViewModel.watchLoginResponse().observe(viewLifecycleOwner) {
            binding.login.isEnabled = true

            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { loginResponse ->
                        cacheViewModel.insert(Cache("token", loginResponse.token))
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let { message ->
                        view?.snack(message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }

        cacheViewModel.watchIsAdded().observe(viewLifecycleOwner) {
            if (it) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                NavHostFragment.findNavController(this).navigate(action)
            }
        }
    }
}