package by.a_makarevich.rsschooltask5catsretrofit.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.a_makarevich.rsschooltask5catsretrofit.Data.CatsData
import by.a_makarevich.rsschooltask5catsretrofit.R
import coil.api.load

class CatAdapter(private val onCatClickListener: OnCatClickListener) : RecyclerView.Adapter<CatViewHolder>() {

    private val items = mutableListOf<CatsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, null)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val imageUrl = items[position].imageUrl
        holder.bind(imageUrl)
        holder.itemView.setOnClickListener {
            onCatClickListener.onItemClick(imageUrl)
        }
    }

    fun addItems(newItems: List<CatsData>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = view.findViewById<ImageView>(R.id.imageView)
    // private val textView = view.findViewById<TextView>(R.id.textView)

    fun bind(imageURL: String) {
        imageView.load(imageURL)
    }

}
interface OnCatClickListener {
    fun onItemClick(imageUrl: String)
}