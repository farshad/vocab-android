package xyz.farshad.vocab.ui.chapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.farshad.vocab.data.entity.Chapter
import xyz.farshad.vocab.databinding.ChapterItemBinding

class ChapterAdopter : RecyclerView.Adapter<ChapterAdopter.IconViewHolder>() {
    private lateinit var context: Context

    inner class IconViewHolder(val binding: ChapterItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Chapter>() {
        override fun areItemsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        context = parent.context
        return IconViewHolder(
            ChapterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Chapter) -> Unit)? = null

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            title.text = item.title
        }
        holder.binding.chapterLy.setOnClickListener {
            onItemClickListener?.let { it(item) }
        }
    }

    fun setOnItemClickListener(listener: (Chapter) -> Unit) {
        onItemClickListener = listener
    }
}

