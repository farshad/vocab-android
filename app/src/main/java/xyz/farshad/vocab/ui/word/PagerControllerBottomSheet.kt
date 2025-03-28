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
import xyz.farshad.vocab.viewmodel.util.Constants.Companion.DEFAULT_SPEECH_RATE

class PagerControllerBottomSheet : BottomSheetDialogFragment() {
    private var layout: PagerControllerBottomSheetBinding? = null
    private val binding get() = layout!!
    private var defaultVelocity: Long = DEFAULT_PAGE_SWITCHER_TIMER
    private var defaultSpeechRate: Float = DEFAULT_SPEECH_RATE
    private var isSoundEnabled: Boolean = true
    var listener: PagerSettingsListener? = null

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
        binding.speechRateNumber.text = defaultSpeechRate.toString()
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
        binding.decreaseRate.setOnClickListener {
            defaultSpeechRate = ((defaultSpeechRate.toBigDecimal() - 0.1.toBigDecimal()).toFloat()).coerceIn(0.1F, 2.0F)
            binding.speechRateNumber.text = defaultSpeechRate.toString()
        }

        binding.increaseRate.setOnClickListener {
            defaultSpeechRate = ((defaultSpeechRate.toBigDecimal() + 0.1.toBigDecimal()).toFloat()).coerceIn(0.1F, 2.0F)
            binding.speechRateNumber.text = defaultSpeechRate.toString()
        }

        binding.soundStatus.setOnClickListener {
            isSoundEnabled = !isSoundEnabled
            if (isSoundEnabled) {
                binding.soundStatus.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context!!,
                        R.drawable.ic_volume_up_black_24dp
                    )
                )
            } else {
                binding.soundStatus.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context!!,
                        R.drawable.baseline_volume_off_24
                    )
                )
            }
        }

        binding.cancel.setOnClickListener { dismiss() }

        binding.save.setOnClickListener {
            listener?.onPagerSettingsChanged(defaultVelocity, defaultSpeechRate, isSoundEnabled)
            dismiss()
        }
    }

    fun setDefaultVelocity(velocity: Long) {
        defaultVelocity = velocity
    }
    fun setDefaultSpeechRate(speechRate: Float) {
        defaultSpeechRate = speechRate
    }

    fun setIsSoundEnabled(soundStatus: Boolean) {
        isSoundEnabled = soundStatus
    }

    companion object {
        const val TAG = "PagerControllerBottomSheet"
    }
}