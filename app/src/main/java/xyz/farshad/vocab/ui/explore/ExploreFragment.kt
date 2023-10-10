package xyz.farshad.vocab.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.databinding.FragmentExploreBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.CourseViewModel

class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    private val courseViewModel: CourseViewModel by viewModel()

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExploreBinding = FragmentExploreBinding.inflate(inflater, container, false)

    override fun bindView() {
    }

    override fun businessLogic() {
        courseViewModel.fetchAll()
        setObserver()
//        binding.explore.setOnClickListener {
//
//        }
    }

    private fun setObserver() {
        courseViewModel.watchCourse()?.observe(this) {
            //showCategoryList(it)
        }
    }

}