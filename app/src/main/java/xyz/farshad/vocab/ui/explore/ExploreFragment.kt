package xyz.farshad.vocab.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import xyz.farshad.vocab.viewmodel.util.Helper.snack
import io.neoattitude.defio.util.Resource
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
        progressLoading = binding.progressBar
    }

    override fun businessLogic() {
        setExploreAdopter()
        setObserver()
        courseViewModel.getAll()
    }

    private fun setObserver() {
        courseViewModel.watchCourseResponses().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let {
                        exploreAdopter.differ.submitList(it)
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

        courseViewModel.watchIsAdded().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
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
    }

    private fun setExploreAdopter() {
        exploreAdopter = ExploreAdopter()
        binding.rvExplore.apply {
            adapter = exploreAdopter
        }

        exploreAdopter.setOnDownloadClickListener {
            courseViewModel.addToSelection(it)
        }
    }
}