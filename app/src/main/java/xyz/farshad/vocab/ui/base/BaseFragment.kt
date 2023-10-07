package xyz.farshad.vocab.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var layout: T? = null
    protected val binding get() = layout!!
    protected var progressLoading: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = this.setViewBinding(inflater, container)
        return binding.root
    }

    abstract fun setViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    abstract fun businessLogic()

    abstract fun bindView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        businessLogic()
        bindView()
    }

    fun hideProgressBar() {
        progressLoading?.apply {
            visibility = View.GONE
        }
    }

    fun showProgressBar() {
        progressLoading?.apply {
            visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        layout = null
    }

    fun setupToolbar(title: String, titleView: TextView, backIcon: ImageView) {
        titleView.text = title
        backIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}