package by.a_makarevich.rsschooltask5catsretrofit

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WebAccess {
  val catsApi : TheCatApi by lazy {
      Log.d("LOG_TAG", "Creating retrofit client")
      val retrofit = Retrofit.Builder()
          .baseUrl("https://api.thecatapi.com/v1/")
          .addConverterFactory(MoshiConverterFactory.create())
          .addCallAdapterFactory(CoroutineCallAdapterFactory())
          .build()
      return@lazy retrofit.create(TheCatApi::class.java)
  }
}