package xyz.farshad.vocab.ui.word

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.databinding.FragmentWordBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.ui.home.HomeFragmentDirections
import xyz.farshad.vocab.viewmodel.WordViewModel

class WordFragment : BaseFragment<FragmentWordBinding>() {
    private val wordViewModel: WordViewModel by viewModel()
    private val args: WordFragmentArgs by navArgs()
    private lateinit var wordAdopter: WordAdopter

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentWordBinding = FragmentWordBinding.inflate(inflater, container, false)

    override fun bindView() {
    }

    override fun businessLogic() {
        setChapterAdopter()
        wordViewModel.findByLevelId(args.chapterId)
        setObserver()
    }

    private fun setObserver() {
        wordViewModel.watchWord()?.observe(this, {
            wordAdopter.differ.submitList(it)
        })
    }

    private fun setChapterAdopter() {
        wordAdopter = WordAdopter()
        binding.rvWord.apply {
            adapter = wordAdopter
        }

        wordAdopter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToChapterFragment(it.id!!)
            NavHostFragment.findNavController(this).navigate(action)
        }
    }
}