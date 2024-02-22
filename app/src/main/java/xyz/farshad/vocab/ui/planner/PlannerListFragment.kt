package xyz.farshad.vocab.ui.planner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
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
        binding.newPlan.setOnClickListener {
            val action = PlannerListFragmentDirections.actionPlannerListFragmentToNewPlanFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

}