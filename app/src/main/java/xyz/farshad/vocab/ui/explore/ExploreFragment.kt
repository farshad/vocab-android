package xyz.farshad.vocab.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.databinding.FragmentExploreBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.CourseViewModel

class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    private val courseViewModel: CourseViewModel by viewModel()
    private lateinit var exploreAdopter: ExploreAdopter

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

    private fun setExploreAdopter() {
        exploreAdopter = ExploreAdopter()
        binding.rvExplore.apply {
            adapter = exploreAdopter
        }

//        countryAdopter.bookmark {
//            val newIndex = countryPairs.indexOf(it)
//            countryPairs[newIndex].country.bookmarked = !it.country.bookmarked!!
//            countryAdopter.notifyItemChanged(newIndex)
//            countryViewModel.update(countryPairs[newIndex].country)
//        }
    }

}