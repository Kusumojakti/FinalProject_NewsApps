package com.example.newsapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapps.ArticlesItem
import com.example.newsapps.R

class AdapterNews(private var dataNews: List<ArticlesItem?>?) : RecyclerView.Adapter<AdapterNews.MyViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class MyViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvTittle = view.findViewById<TextView>(R.id.tv_title)
        val tvDescription = view.findViewById<TextView>(R.id.tv_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fetch_item_news, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataNews?.get(position)

        item?.let { nonNullItem ->
            holder.tvTittle.text = nonNullItem.title
            holder.tvDescription.text = nonNullItem.description
        }
    }

    fun updateData(newData: List<ArticlesItem?>?) {
        dataNews = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataNews?.count { it != null } ?: 0
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ArticlesItem)
    }

    //Fungsi baru untuk menerapkan hasil search
    fun setFilteredList(mList: ArrayList<ArticlesItem>) {
        //PERUBAHAN KODE
        dataNews = mList
        notifyDataSetChanged()
    }

}