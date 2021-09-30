package by.a_makarevich.rsschooltask5catsretrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.a_makarevich.rsschooltask5catsretrofit.databinding.ActivityMainBinding
import by.a_makarevich.rsschooltask5catsretrofit.pagination.OnCatClickListener
import by.a_makarevich.rsschooltask5catsretrofit.pagination.RemoteCatImageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnCatClickListener {

    private lateinit var binding: ActivityMainBinding

    lateinit var remoteViewModel: RemoteViewModel
    lateinit var adapter: RemoteCatImageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMembers()
        setUpViews()
        fetchCatImages()

    }

    private fun setUpViews() {
        recyclerView.adapter = adapter
    }

    private fun initMembers() {
        remoteViewModel = defaultViewModelProviderFactory.create(RemoteViewModel::class.java)
        adapter = RemoteCatImageAdapter(this)
    }

    private fun fetchCatImages() {
        lifecycleScope.launch {
            remoteViewModel.fetchCatImages().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }


    override fun onItemClick(imageUrl: String) {
        Log.d("LOG_TAG", "ITEM_CLICKED $imageUrl")
        val intent = Intent(this, CatViewActivity::class.java)
        intent.putExtra("IMAGE_URL", imageUrl)
        startActivity(intent)
    }
}