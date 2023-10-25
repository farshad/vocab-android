package xyz.farshad.vocab.ui.word

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.farshad.vocab.data.entity.Word
import xyz.farshad.vocab.databinding.WordItemBinding

class WordAdopter : RecyclerView.Adapter<WordAdopter.IconViewHolder>() {
    private lateinit var context: Context

    inner class IconViewHolder(val binding: WordItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        context = parent.context
        return IconViewHolder(
            WordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Word) -> Unit)? = null

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            title.text = item.title
        }
        holder.binding.wordLy.setOnClickListener {
            onItemClickListener?.let { it(item) }
        }
    }

    fun setOnItemClickListener(listener: (Word) -> Unit) {
        onItemClickListener = listener
    }
}

