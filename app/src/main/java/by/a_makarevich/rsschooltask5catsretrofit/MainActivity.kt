package by.a_makarevich.rsschooltask5catsretrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.a_makarevich.rsschooltask5catsretrofit.adapter.CatAdapter
import by.a_makarevich.rsschooltask5catsretrofit.adapter.OnCatClickListener
import by.a_makarevich.rsschooltask5catsretrofit.data.Cat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), OnCatClickListener {

    private val viewModel: MainViewModel by viewModels()

    private val mAdapter = CatAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setObservers()
        viewModel.getCats()

    }

    private fun setObservers() {
        viewModel.catList.observe(this, Observer {
            updateAdapter(it)
        })

        viewModel.loadingState.observe(this, Observer {
            setLoading(it)
        })
    }

    private fun updateAdapter(cats: List<Cat>) {
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            mAdapter.addItems(cats)
        }
    }

    private fun setLoading(loading: Boolean) {
        progressBar_loader?.isVisible = loading
    }

    override fun onItemClick(imageUrl: String) {
        Log.d("LOG_TAG", "ITEM_CLICKED $imageUrl")
        val intent = Intent(this, CatViewActivity::class.java)
        intent.putExtra("IMAGE_URL", imageUrl)
        startActivity(intent)
    }
}