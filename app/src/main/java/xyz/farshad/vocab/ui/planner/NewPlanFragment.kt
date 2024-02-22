package xyz.farshad.vocab.ui.planner

import android.view.LayoutInflater
import android.view.ViewGroup
import xyz.farshad.vocab.databinding.FragmentNewPlanBinding
import xyz.farshad.vocab.ui.base.BaseFragment

class NewPlanFragment : BaseFragment<FragmentNewPlanBinding>() {

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentNewPlanBinding = FragmentNewPlanBinding.inflate(inflater, container, false)

    override fun bindView() {
    }

    override fun businessLogic() {
    }


}