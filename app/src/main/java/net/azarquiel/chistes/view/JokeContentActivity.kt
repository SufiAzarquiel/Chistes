package net.azarquiel.chistes.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import net.azarquiel.chistes.R
import net.azarquiel.chistes.model.Category
import net.azarquiel.chistes.model.Joke

class JokeContentActivity : AppCompatActivity() {
    private lateinit var category: Category
    private lateinit var ivCat: ImageView
    private lateinit var tvCat: TextView
    private lateinit var tvContent: TextView
    private lateinit var joke: Joke

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke_content)

        joke = intent.getSerializableExtra("joke") as Joke
        category = intent.getSerializableExtra("category") as Category // deprecated

        findViews()
        setViews()
    }

    private fun findViews() {
        tvContent = findViewById(R.id.tvContent)
        tvCat = findViewById(R.id.tvContentCat)
        ivCat = findViewById(R.id.ivCat)
    }

    private fun setViews() {
        tvCat.text = category.name
        // display html in content textview
        tvContent.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(joke.content , Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(joke.content, Html.FROM_HTML_MODE_LEGACY)
        }

        // load image from url
        var url = "http://www.ies-azarquiel.es/paco/apichistes/img/${joke.catId}.png"
        Picasso.get()
            .load(url)
            .into(ivCat)
    }
}