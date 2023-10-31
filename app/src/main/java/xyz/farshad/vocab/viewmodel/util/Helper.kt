package xyz.farshad.vocab.viewmodel.util

import android.content.Context
import android.content.res.ColorStateList
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import xyz.farshad.vocab.R

object Helper {
    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }

    fun decorator(context: Context): RecyclerView.ItemDecoration {
        val decorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)

        return decorator
    }

    fun countToString(count: Int): String {
        return when (count) {
            in 1000..9999 -> {
                count.toString().substring(0, 1) + "k"
            }
            in 10000..99999 -> {
                count.toString().substring(0, 2) + "k"
            }
            in 100000..999999 -> {
                count.toString().substring(0, 3) + "k"
            }
            in 1000000..9999999 -> {
                count.toString().substring(0, 1) + "m"
            }
            else -> {
                count.toString()
            }
        }
    }

    fun checkEmptyField(tv: TextInputEditText, ly: TextInputLayout): Boolean {
        var result = true
        if (TextUtils.isEmpty(tv.text)) {
            ly.error = "This field is required"
            result = false
        }
        return result
    }

    fun checkFieldMinLength(tv: TextInputEditText, ly: TextInputLayout, length: Int): Boolean {
        var result = true
        if (tv.text.toString().length < length) {
            ly.error = "The field must be at least $length characters long"
            result = false
        }
        return result
    }

    fun isValidEmail(tv: TextInputEditText, ly: TextInputLayout): Boolean {
        var result = true
        if (tv.text.toString().isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(tv.text.toString()).matches()) {
            ly.error = "Invalid email format"
            result = false
        }
        return result
    }

    fun clearError(vararg lys: TextInputLayout) {
        for (ly in lys) {
            ly.error = null
        }
    }

    fun createColorStateList(colors: IntArray): ColorStateList {
        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_pressed)
        )

        return ColorStateList(states, colors)
    }
}

