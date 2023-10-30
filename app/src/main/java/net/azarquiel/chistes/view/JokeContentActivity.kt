package net.azarquiel.chistes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        tvContent.text = joke.content
        tvCat.text = category.name

        // load image from url
        var url = "http://www.ies-azarquiel.es/paco/apichistes/img/${joke.catId}.png"
        Picasso.get()
            .load(url)
            .into(ivCat)
    }
}