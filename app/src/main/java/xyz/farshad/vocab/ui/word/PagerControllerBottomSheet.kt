package xyz.farshad.vocab.ui.word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import xyz.farshad.vocab.R
import xyz.farshad.vocab.databinding.PagerControllerBottomSheetBinding
import xyz.farshad.vocab.viewmodel.util.Constants.Companion.DEFAULT_PAGE_SWITCHER_TIMER

class PagerControllerBottomSheet : BottomSheetDialogFragment() {
    private var layout: PagerControllerBottomSheetBinding? = null
    private val binding get() = layout!!
    private var defaultVelocity: Long = DEFAULT_PAGE_SWITCHER_TIMER
    private var isSoundEnabled: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        layout = this.setViewBinding(inflater, container)
        return binding.root
    }

    private fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): PagerControllerBottomSheetBinding =
        PagerControllerBottomSheetBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.velocityNumber.text = defaultVelocity.toString()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.decrease.setOnClickListener {
            defaultVelocity = (defaultVelocity - 1).coerceIn(1, 10)
            binding.velocityNumber.text = defaultVelocity.toString()
        }

        binding.increase.setOnClickListener {
            defaultVelocity = (defaultVelocity + 1).coerceIn(1, 10)
            binding.velocityNumber.text = defaultVelocity.toString()
        }

        binding.soundStatus.setOnClickListener {
            isSoundEnabled = !isSoundEnabled
            if (isSoundEnabled) {
                binding.soundStatus.setImageDrawable(AppCompatResources.getDrawable(context!!, R.drawable.ic_volume_up_black_24dp))
            } else {
                binding.soundStatus.setImageDrawable(AppCompatResources.getDrawable(context!!, R.drawable.baseline_volume_off_24))
            }
        }
    }

    fun setDefaultVelocity(velocity: Long) {
        defaultVelocity = velocity
    }

    fun setIsSoundEnabled(soundStatus: Boolean) {
        isSoundEnabled = soundStatus
    }

    companion object {
        const val TAG = "PagerControllerBottomSheet"
    }
}