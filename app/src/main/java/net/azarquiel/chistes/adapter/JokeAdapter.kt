package net.azarquiel.chistes.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.chistes.R
import net.azarquiel.chistes.model.Joke

class JokeAdapter(val context: Context,
                    val layout: Int
) : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    private var dataList: List<Joke> = emptyList()

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

    internal fun setJokes(jokes: List<Joke>) {
        this.dataList = jokes
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Joke){
            // create view references
            val ivRowJokeIcon = itemView.findViewById(R.id.ivRowJokeIcon) as ImageView
            val tvRowContent = itemView.findViewById(R.id.tvRowContent) as TextView

            // check if content is too long
            var content = if (dataItem.content.length > 40) {
                dataItem.content.substring(0,40) + "..."
            } else {
                dataItem.content
            }
            // display html in content textview
            tvRowContent.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(content , Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
            }

            // load image from url
            var url = "http://www.ies-azarquiel.es/paco/apichistes/img/${dataItem.catId}.png"
            Picasso.get()
                .load(url)
                .into(ivRowJokeIcon)

            // set tag
            itemView.tag = dataItem
        }
    }
}