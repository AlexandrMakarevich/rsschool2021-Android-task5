package by.a_makarevich.rsschooltask5catsretrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.a_makarevich.rsschooltask5catsretrofit.Adapter.CatAdapter
import by.a_makarevich.rsschooltask5catsretrofit.Adapter.OnCatClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity(), OnCatClickListener {

    private val itemAdapter = CatAdapter(this)

    private val scope = CoroutineScope(Dispatchers.Main)

    private fun loadCatsAndUpdateList() {

        scope.launch {

            WebAccess.getFlowListOfCats().collect {
                itemAdapter.addItems(it)
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