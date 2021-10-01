package by.a_makarevich.rsschooltask5catsretrofit.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.a_makarevich.rsschooltask5catsretrofit.R
import by.a_makarevich.rsschooltask5catsretrofit.model.Cat
import coil.api.load

class RemoteCatImageAdapter(private val onCatClickListener: OnCatClickListener) :
    PagingDataAdapter<Cat, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CatImageViewHolder).bind(item = getItem(position))
        holder.itemView.setOnClickListener {
            onCatClickListener.onItemClick(getItem(position)!!.imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CatImageViewHolder.getInstance(parent)
    }
}

class CatImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun getInstance(parent: ViewGroup): CatImageViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.layout_item, parent, false)
            return CatImageViewHolder(view)
        }
    }

    private val catImage = view.findViewById<ImageView>(R.id.catImageView)

    fun bind(item: Cat?) {
        catImage.load(item?.imageUrl) {
            placeholder(R.drawable.ic_launcher_foreground)
        }
    }
}

interface OnCatClickListener {
    fun onItemClick(imageUrl: String)
}
