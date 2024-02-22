package xyz.farshad.vocab.ui.word

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.data.entity.Word
import xyz.farshad.vocab.databinding.FragmentWordBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.WordViewModel
import xyz.farshad.vocab.viewmodel.util.Helper

class WordFragment : BaseFragment<FragmentWordBinding>() {
    private val wordViewModel: WordViewModel by viewModel()
    private val args: WordFragmentArgs by navArgs()
    private lateinit var wordAdopter: WordAdopter
    private var words: List<Word> = arrayListOf()

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

        binding.includeToolbarInner.menuIcon.setOnClickListener { view ->
            val popupMenu = PopupMenu(requireContext(), view)
            popupMenu.inflate(R.menu.menu_word_list)
            popupMenu.setForceShowIcon(true)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.starred -> {
                        words = words.filter { w: Word -> w.isFavorite }
                        wordAdopter.differ.submitList(words)
                        true
                    }
                    R.id.shuffle -> {
                        words = words.shuffled()
                        wordAdopter.differ.submitList(words)
                        true
                    }
                    R.id.default_ -> {
                        wordViewModel.findByChapterId(args.chapterId)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    override fun businessLogic() {
        setWordAdopter()
        wordViewModel.findByChapterId(args.chapterId)
        setObserver()
    }

    private fun setObserver() {
        wordViewModel.watchWord()?.observe(viewLifecycleOwner) {
            wordAdopter.differ.submitList(it)
            words = it
        }
    }

    private fun setWordAdopter() {
        binding.rvWord.addItemDecoration(
            Helper.decorator(requireContext())
        )
        wordAdopter = WordAdopter()
        binding.rvWord.apply {
            adapter = wordAdopter
        }

        wordAdopter.setOnItemClickListener {
            val action = WordFragmentDirections.actionWordFragmentToWordPagerFragment(
                it,
                args.chapterId,
                args.lang,
                words.toTypedArray()
            )
            NavHostFragment.findNavController(this).navigate(action)
        }
    }
}