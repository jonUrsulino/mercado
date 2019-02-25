package com.jon.mercado

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jon.mercado.model.IServicesAPI
import com.jon.mercado.model.bean.SearchItemsBean

import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SearchActivity"
        private const val EXTRA_QUERY = "QUERY"

        fun createIntent(context: Context, query: String): Intent {
            val intent = Intent(context, SearchActivity::class.java)
            intent.putExtra(EXTRA_QUERY, query)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val search = retrofit.create(IServicesAPI::class.java)

        val query = intent.getStringExtra(EXTRA_QUERY)

        if (query != null && query.isNotBlank()) {
            val searchItems = search.getSearchItems(query)
            searchItems.enqueue(object : Callback<SearchItemsBean> {
                override fun onFailure(call: Call<SearchItemsBean>, t: Throwable) {
                    Log.e(TAG, "Ocorreu um erro na busca ${t.message}")
                }

                override fun onResponse(call: Call<SearchItemsBean>, response: Response<SearchItemsBean>) {
                    Log.d(TAG, "Request busca: ")
                    response.body()?.let { initWithItems(it) }
                }
            })
        }

    }

    private fun initWithItems(searchItems: SearchItemsBean) {
        Log.d(TAG, "Resultado busca: ")
        val linearLayoutManager = LinearLayoutManager(this).apply {
            orientation = RecyclerView.VERTICAL
        }
        searchRecyclerView.layoutManager = linearLayoutManager
        searchRecyclerView.adapter = SearchAdapter(searchItems.results)
        searchRecyclerView.setHasFixedSize(true)
    }

}
