package xyz.farshad.vocab.ui.word

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.databinding.FragmentWordBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.WordViewModel
import xyz.farshad.vocab.viewmodel.util.Helper

class WordFragment : BaseFragment<FragmentWordBinding>() {
    private val wordViewModel: WordViewModel by viewModel()
    private val args: WordFragmentArgs by navArgs()
    private lateinit var wordAdopter: WordAdopter

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentWordBinding = FragmentWordBinding.inflate(inflater, container, false)

    override fun bindView() {
        setupToolbar(
            getString(R.string.chapter) + " " + args.chapterTitle,
            binding.includeToolbarInner.innerToolbarTitle,
            binding.includeToolbarInner.backIcon
        )
    }

    override fun businessLogic() {
        setChapterAdopter()
        wordViewModel.findByChapterId(args.chapterId)
        setObserver()
    }

    private fun setObserver() {
        wordViewModel.watchWord()?.observe(viewLifecycleOwner) {
            wordAdopter.differ.submitList(it)
        }
    }

    private fun setChapterAdopter() {
        binding.rvWord.addItemDecoration(
            Helper.decorator(requireContext())
        )
        wordAdopter = WordAdopter()
        binding.rvWord.apply {
            adapter = wordAdopter
        }

        wordAdopter.setOnItemClickListener {
            val action = WordFragmentDirections.actionWordFragmentToWordPagerFragment(
                it.id!!,
                args.chapterId
            )
            NavHostFragment.findNavController(this).navigate(action)
        }
    }
}