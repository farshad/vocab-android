package xyz.farshad.vocab.ui.explore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.farshad.vocab.data.model.Course
import xyz.farshad.vocab.databinding.ExploreItemBinding

class ExploreAdopter : RecyclerView.Adapter<ExploreAdopter.IconViewHolder>() {
    private lateinit var context: Context

    inner class IconViewHolder(val binding: ExploreItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.equals(newItem)
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        context = parent.context
        return IconViewHolder(
            ExploreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Course) -> Unit)? = null

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            catTextView.text = item.name
        }
        holder.binding.courseLy.setOnClickListener {
            onItemClickListener?.let { it(item) }
        }
    }

    fun setOnItemClickListener(listener: (Course) -> Unit) {
        onItemClickListener = listener
    }
}

