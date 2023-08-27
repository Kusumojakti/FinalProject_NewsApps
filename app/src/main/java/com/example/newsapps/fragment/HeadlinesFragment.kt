package com.example.newsapps.fragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapps.API.APIConfig
import com.example.newsapps.ArticlesItem
import com.example.newsapps.R
import com.example.newsapps.ResponseNews
import com.example.newsapps.ThemeActivity
import com.example.newsapps.adapter.AdapterNews
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class HeadlinesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterNews: AdapterNews
    private lateinit var shimmerFrameLayout : ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_headlines, container, false)
        recyclerView = view.findViewById(R.id.rv_news)

        adapterNews = AdapterNews(ArrayList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapterNews
        shimmerFrameLayout = view.findViewById(R.id.shimmer_rv)
//        searchView = view.findViewById(R.id.searchview)


        getData()
        setHasOptionsMenu(true)

        return view
    }

    private fun getData() {
        APIConfig.getService().newsHeadline().enqueue(object : Callback<ResponseNews> {
            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                if (response.isSuccessful) {
                    val responseNews = response.body()
                    val dataItem = responseNews?.articles

                    if (dataItem != null) {
                        adapterNews.updateData(dataItem)
                    }
                    shimmerFrameLayout.startShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                Toast.makeText(requireContext(), "Data not found", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.darkmode -> {
                val intent = Intent(requireContext(),ThemeActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
//    private fun searchnews() {
//
//    }
}