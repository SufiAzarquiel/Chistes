package net.azarquiel.chistes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.chistes.R
import net.azarquiel.chistes.adapter.JokeAdapter
import net.azarquiel.chistes.model.Joke
import net.azarquiel.chistes.model.Category
import net.azarquiel.chistes.model.JokesResult
import java.net.URL

class JokesActivity : AppCompatActivity() {
    private var category: Category? = null
    private lateinit var adapter: JokeAdapter
    private lateinit var rvJokes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)

        category = intent.getSerializableExtra("category") as Category? // deprecated

        rvJokes = findViewById(R.id.rvJokes)

        initRv()
        getData()
    }

    private fun initRv() {
        adapter = JokeAdapter(this, R.layout.row_joke)
        rvJokes.adapter = adapter
        rvJokes.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        GlobalScope.launch {
            // Get JSON data from the internet REMEMBER TO ASK FOR INTERNET PERMISSION
            val jsonTxt = URL(
                "http://www.ies-azarquiel.es/paco/apichistes/categoria/${category!!.id}/chistes"
            ).readText(Charsets.UTF_8)
            // Parse JSON data with GSON library and pass it to the adapter
            launch(Dispatchers.Main) {
                var result = Gson().fromJson(jsonTxt, JokesResult::class.java)
                adapter.setJokes(result.jokes.toList())
            }
        }
    }

    fun onClickJoke(v: View) {
        val joke = v.tag as Joke
        val intent = Intent(this, JokeContentActivity::class.java)
        intent.putExtra("joke", joke)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}