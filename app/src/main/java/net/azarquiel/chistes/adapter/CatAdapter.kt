package net.azarquiel.chistes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.chistes.R
import net.azarquiel.chistes.model.Category

class CatAdapter(val context: Context,
                 val layout: Int
) : RecyclerView.Adapter<CatAdapter.ViewHolder>() {

    private var dataList: List<Category> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setCategories(categories: List<Category>) {
        this.dataList = categories
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Category){
            // create view references
            val ivRowIcon = itemView.findViewById(R.id.ivRowJokeIcon) as ImageView
            val tvRowName = itemView.findViewById(R.id.tvRowContent) as TextView

            // set values
            tvRowName.text = dataItem.name
            var url = "http://www.ies-azarquiel.es/paco/apichistes/img/${dataItem.id}.png"
            // load image from url
            Picasso.get()
                .load(url)
                .into(ivRowIcon)

            // add category to tag
            itemView.tag = dataItem
        }
    }
}