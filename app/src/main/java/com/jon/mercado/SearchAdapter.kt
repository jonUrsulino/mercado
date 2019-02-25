package com.jon.mercado

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jon.mercado.model.bean.SearchItemBean
import com.squareup.picasso.Picasso

class SearchAdapter(val listItems: List<SearchItemBean>): RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {


    override fun getItemCount(): Int = listItems.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_search_item, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val searchItemBean = listItems[position]
        holder.bind(searchItemBean)
    }

    class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        var image : ImageView = view.findViewById(R.id.imageThumbail)
        var title : TextView = view.findViewById(R.id.titleText)
        var valueText : TextView = view.findViewById(R.id.valueText)
        var installments : TextView = view.findViewById(R.id.installmentsText)
        var freeShipping : TextView = view.findViewById(R.id.freeShippingText)

        fun bind(searchItemBean: SearchItemBean) {

            Picasso.with(view.context).load(searchItemBean.thumbnail).into(image)

            title.text = searchItemBean.title
            valueText.text = "R$ ${searchItemBean.price}"
            installments.text = searchItemBean.title

            val isVisible = if (searchItemBean.shipping != null && searchItemBean.shipping.free_shipping) View.VISIBLE
            else View.INVISIBLE

            freeShipping.visibility = isVisible
        }

    }
}