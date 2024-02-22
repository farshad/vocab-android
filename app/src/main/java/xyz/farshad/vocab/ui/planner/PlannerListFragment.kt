package xyz.farshad.vocab.ui.planner

import android.view.LayoutInflater
import android.view.ViewGroup
import xyz.farshad.vocab.databinding.FragmentPlannerListBinding
import xyz.farshad.vocab.ui.base.BaseFragment

class PlannerListFragment : BaseFragment<FragmentPlannerListBinding>() {

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentPlannerListBinding = FragmentPlannerListBinding.inflate(inflater, container, false)

    override fun bindView() {
    }

    override fun businessLogic() {
    }

}