package xyz.farshad.vocab.ui.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import xyz.farshad.vocab.databinding.FragmentLoginBinding
import xyz.farshad.vocab.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun setViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun businessLogic() {
    }

    override fun bindView() {
    }

}