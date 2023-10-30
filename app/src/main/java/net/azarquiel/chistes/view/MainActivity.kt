package net.azarquiel.chistes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.chistes.R
import net.azarquiel.chistes.adapter.CatAdapter
import net.azarquiel.chistes.model.Category
import net.azarquiel.chistes.model.CatsResult
import java.net.URL

class MainActivity : AppCompatActivity() {
    private var category: Category? = null
    private lateinit var catAdapter: CatAdapter
    private lateinit var rvCategories: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCategories = findViewById(R.id.rvCategories)

        initRv()
        getData()
    }

    private fun initRv() {
        catAdapter = CatAdapter(this, R.layout.row_category)
        rvCategories.adapter = catAdapter
        rvCategories.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        GlobalScope.launch {
            // Get JSON data from the internet REMEMBER TO ASK FOR INTERNET PERMISSION
            val jsontxt = URL(
                "http://www.ies-azarquiel.es/paco/apichistes/categorias"
            ).readText(Charsets.UTF_8)
            // Parse JSON data with GSON library and pass it to the adapter
            launch(Main) {
                var catsResult = Gson().fromJson(jsontxt, CatsResult::class.java)
                catAdapter.setCategories(catsResult.categories.toList())
            }
        }
    }

    fun onClickCategory(v: View) {
        category = v.tag as Category;

        var intent = Intent(this, JokesActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}