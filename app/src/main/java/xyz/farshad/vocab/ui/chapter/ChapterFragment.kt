package xyz.farshad.vocab.ui.chapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.databinding.FragmentChapterBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.ChapterViewModel

class ChapterFragment : BaseFragment<FragmentChapterBinding>() {

    private val levelViewModel: ChapterViewModel by viewModel()
    private val args: ChapterFragmentArgs by navArgs()
    private lateinit var chapterAdopter: ChapterAdopter

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentChapterBinding = FragmentChapterBinding.inflate(inflater, container, false)

    override fun bindView() {
        setupToolbar(
            getString(R.string.chapters),
            binding.includeToolbarInner.innerToolbarTitle,
            binding.includeToolbarInner.backIcon
        )
    }

    override fun businessLogic() {
        setChapterAdopter()
        levelViewModel.findByCourseId(args.courseId)
        setObserver()
    }

    private fun setObserver() {
        levelViewModel.watchLevel()?.observe(this) {
            chapterAdopter.differ.submitList(it)
        }
    }

    private fun setChapterAdopter() {
        chapterAdopter = ChapterAdopter()
        binding.rvChapter.apply {
            adapter = chapterAdopter
        }

        chapterAdopter.setOnItemClickListener {
            val action = ChapterFragmentDirections.actionChapterFragmentToWordFragment(it.id!!, it.title)
            NavHostFragment.findNavController(this).navigate(action)
        }
    }
}