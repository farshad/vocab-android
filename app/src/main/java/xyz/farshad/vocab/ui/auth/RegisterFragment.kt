package xyz.farshad.vocab.ui.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import xyz.farshad.vocab.databinding.FragmentRegisterBinding
import xyz.farshad.vocab.ui.base.BaseFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override fun setViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun businessLogic() {
    }

    override fun bindView() {
    }
}