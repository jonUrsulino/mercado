package com.jon.mercado

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.jon.mercado.model.IServiceSearch
import com.jon.mercado.model.bean.SearchItemsBean

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val search = retrofit.create(IServiceSearch::class.java)

        button.setOnClickListener {
            val text = editText.text.toString()
            val callSearchItems = search.getSearchItems(text)

            callSearchItems.enqueue(object : Callback<SearchItemsBean> {
                override fun onFailure(call: Call<SearchItemsBean>, t: Throwable) {
                    Snackbar.make(it, "Erro ${t.message}", Snackbar.LENGTH_LONG)
                        .setAction("Ok", null).show()
                }

                override fun onResponse(call: Call<SearchItemsBean>, response: Response<SearchItemsBean>) {
                    val searchItemsBean = response.body()

                    Snackbar.make(it, "Resultado 1: ${searchItemsBean?.results?.get(0)?.title}", Snackbar.LENGTH_LONG)
                        .setAction("Ok", null).show()
                }
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
