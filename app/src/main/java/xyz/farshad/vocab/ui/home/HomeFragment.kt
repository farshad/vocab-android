package xyz.farshad.vocab.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.databinding.FragmentHomeBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.CourseViewModel
import xyz.farshad.vocab.viewmodel.SyncViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val courseViewModel: CourseViewModel by viewModel()
    private val syncViewModel: SyncViewModel by viewModel()
    private lateinit var courseAdopter: CourseAdopter

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun bindView() {
    }

    override fun businessLogic() {
        setCourseAdopter()
        courseViewModel.fetchAll()
        setObserver()
        binding.explore.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToExploreFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    private fun setObserver() {
        courseViewModel.watchCourses()?.observe(this) {
            courseAdopter.differ.submitList(it)
        }

        syncViewModel.watchSync().observe(this) {
            courseViewModel.fetchAll()
        }
    }

    private fun setCourseAdopter() {
        courseAdopter = CourseAdopter()
        binding.rvCourse.apply {
            adapter = courseAdopter
        }

        courseAdopter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToChapterFragment(it.id!!)
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

}