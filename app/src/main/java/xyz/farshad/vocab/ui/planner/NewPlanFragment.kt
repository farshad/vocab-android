package xyz.farshad.vocab.ui.planner

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import io.neoattitude.defio.util.DateHelper
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.databinding.FragmentNewPlanBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.CourseViewModel
import java.util.*

class NewPlanFragment : BaseFragment<FragmentNewPlanBinding>() {

    private val courseViewModel: CourseViewModel by viewModel()
    private var startPlanDate: Date? = null
    private lateinit var startTimePicker: MaterialTimePicker

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentNewPlanBinding = FragmentNewPlanBinding.inflate(inflater, container, false)

    override fun bindView() {
        setupToolbar(
            getString(R.string.new_plan),
            binding.includeToolbarInner.innerToolbarTitle,
            binding.includeToolbarInner.backIcon
        )
    }

    override fun businessLogic() {
        initializeDatePicker()
        initializeStartTimePicker()
        setObserver()
        courseViewModel.fetchAll()
        binding.notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.notificationTimeLy.visibility = VISIBLE
            } else {
                binding.notificationTimeLy.visibility = GONE
            }
        }
    }

    private fun setObserver() {
        courseViewModel.watchCourses()?.observe(viewLifecycleOwner) {
            val suggestions = it.map { course -> course.title }.toCollection(arrayListOf())

            val adapter: ArrayAdapter<String> =
                ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    suggestions
                )

            binding.title.setAdapter(adapter)
            binding.title.threshold = 1
            binding.title.setDropDownBackgroundResource(R.color.white)
            binding.title.dropDownVerticalOffset = 10
        }
    }

    private fun initializeDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.select_date))
            .build()

        val currentDate = Date()

        binding.planStartDate.setText(
            DateHelper.convertLongToDate(
                currentDate.time,
                "MMM dd, yyyy"
            )
        )

        binding.planStartDate.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (!datePicker.isAdded) {
                    datePicker.show(requireActivity().supportFragmentManager, "DATE_PICKER")
                } else {
                    datePicker.showsDialog
                }
                binding.planStartDate.clearFocus()
            }
        }

        datePicker.addOnPositiveButtonClickListener {
            startPlanDate = Date(it)
            binding.planStartDate.setText(datePicker.headerText)
        }
    }

    private fun initializeStartTimePicker() {
        val currentDate = Date().time
        binding.notificationTime.setText(DateHelper.convertLongToDate(currentDate, "HH:mm"))

        startTimePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(DateHelper.convertLongToDate(currentDate, "HH").toInt())
                .setMinute(DateHelper.convertLongToDate(currentDate, "mm").toInt())
                .setTitleText(getString(R.string.select_notification_time))
                .build()

        startTimePicker.addOnPositiveButtonClickListener {
            binding.notificationTime.setText("${startTimePicker.hour}:${startTimePicker.minute}")
        }

        binding.notificationTime.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (!startTimePicker.isAdded) {
                    startTimePicker.show(
                        requireActivity().supportFragmentManager,
                        "START_TIME_PICKER"
                    )
                } else {
                    startTimePicker.showsDialog
                }
                binding.planStartDate.clearFocus()
            }
        }
    }
}