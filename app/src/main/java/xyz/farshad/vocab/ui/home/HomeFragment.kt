package xyz.farshad.vocab.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.data.model.Course
import xyz.farshad.vocab.databinding.FragmentHomeBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.CourseViewModel
import xyz.farshad.vocab.viewmodel.SyncViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val courseViewModel: CourseViewModel by viewModel()
    private val syncViewModel: SyncViewModel by viewModel()

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun bindView() {
    }

    override fun businessLogic() {
        courseViewModel.fetchAll()
        setObserver()
        binding.sync.setOnClickListener {
            syncViewModel.get()
        }
    }

    private fun setObserver() {
        courseViewModel.watchCourse()?.observe(this) {
            showCategoryList(it)
        }

        syncViewModel.watchSync().observe(this) {
            courseViewModel.fetchAll()
        }
    }

    private fun showCategoryList(courses: List<Course>) {
        val adapter = CourseListAdapter(requireActivity(), R.layout.cuorse_list_view, courses)
        binding.catMainListView.adapter = adapter
        binding.catMainListView.itemsCanFocus = true
    }

}