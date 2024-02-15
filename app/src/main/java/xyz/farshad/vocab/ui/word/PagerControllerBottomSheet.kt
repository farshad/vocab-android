package xyz.farshad.vocab.ui.word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import xyz.farshad.vocab.databinding.PagerControllerBottomSheetBinding
import xyz.farshad.vocab.viewmodel.util.Constants.Companion.DEFAULT_PAGE_SWITCHER_TIMER

class PagerControllerBottomSheet : BottomSheetDialogFragment() {
    private var layout: PagerControllerBottomSheetBinding? = null
    private val binding get() = layout!!
    private var defaultVelocity: Long = DEFAULT_PAGE_SWITCHER_TIMER

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layout = this.setViewBinding(inflater, container)
        return binding.root
    }

    private fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): PagerControllerBottomSheetBinding =
        PagerControllerBottomSheetBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setDefaultValues()
    }

    fun setDefaultVelocity(velocity: Long) {
        defaultVelocity = velocity
    }

    companion object {
        const val TAG = "PagerControllerBottomSheet"
    }
}