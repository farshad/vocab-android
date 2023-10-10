package xyz.farshad.vocab.ui.chapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.data.entity.Chapter
import xyz.farshad.vocab.databinding.FragmentChapterBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.ChapterViewModel

class ChapterFragment : BaseFragment<FragmentChapterBinding>() {

    private val levelViewModel: ChapterViewModel by viewModel()
    private val args: ChapterFragmentArgs by navArgs()
    private var courseId: Long? = null

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChapterBinding = FragmentChapterBinding.inflate(inflater, container, false)

    override fun bindView() {
    }

    override fun businessLogic() {

//        val b = intent.extras
//        if (b != null && b.containsKey("courseId")) {
//            courseId = b.get("courseId").toString().toLong()
            levelViewModel.findByCourseId(args.courseId)
//        }

        setObserver()
    }

    private fun setObserver() {
        levelViewModel.watchLevel()?.observe(this) {
            showLevelList(it)
        }
    }

    private fun showLevelList(chapters: List<Chapter>) {
        val adapter = ChapterListAdapter(requireContext(), R.layout.level_list_view, chapters)
        binding.levelListView.adapter = adapter
        binding.levelListView.itemsCanFocus = true
    }
}