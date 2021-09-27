package by.a_makarevich.rsschooltask5catsretrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.a_makarevich.rsschooltask5catsretrofit.Adapter.CatAdapter
import by.a_makarevich.rsschooltask5catsretrofit.Adapter.OnCatClickListener
import by.a_makarevich.rsschooltask5catsretrofit.Data.CatsData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnCatClickListener {

    private val itemAdapter = CatAdapter(this)

    private fun loadCatsAndUpdateList() {


        GlobalScope.launch(Dispatchers.Main) {
        // Execute web request through coroutine call adapter & retrofit
        val webResponse = WebAccess.catsApi.getListOfCats().await()

            if (webResponse.isSuccessful){
                val catsList: List<CatsData>? = webResponse.body()
                Log.d("LOG_TAG", catsList.toString())
                itemAdapter.addItems(catsList ?: listOf())
                itemAdapter.notifyDataSetChanged()
            } else {
                Log.d("LOG_TAG", "Error ${webResponse.code()}")
                Toast.makeText(this@MainActivity, "Error ${webResponse.code()}", Toast.LENGTH_SHORT).show()
            }
    }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        loadCatsAndUpdateList()
        

    }

    override fun onItemClick(imageUrl: String) {
        Log.d("LOG_TAG", "ITEM_CLICKED $imageUrl")
        val intent = Intent(this, CatViewActivity::class.java)
        intent.putExtra("IMAGE_URL", imageUrl)
        startActivity(intent)
    }
}