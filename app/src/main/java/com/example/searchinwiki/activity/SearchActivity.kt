package com.example.searchinwiki.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchinwiki.R
import com.example.searchinwiki.adapter.SearchResultAdapter
import com.example.searchinwiki.model.SearchResponse
import com.example.searchinwiki.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by Chaitra on 22,June,2021
 */
class SearchActivity : AppCompatActivity() {

    lateinit var adapter: SearchResultAdapter
    lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setupView()

        setUpDataObserver()

        setUpEvents()
    }

    private fun setUpEvents() {
        query_input.doAfterTextChanged {
            clear_query.isVisible = it?.isNotEmpty()!!
            if (it.isBlank()) {
                adapter.bindArticles(emptyList())
            } else {
                viewModel.makeApiCall(query_input.text.toString())
            }
        }

        clear_query.setOnClickListener {
            query_input.setText("")
        }

    }

    private fun setUpDataObserver() {
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        viewModel.getDataObserver().observe(this,
            Observer<SearchResponse> { model ->
                if (model?.query != null && query_input.text.toString().isNotEmpty()) {
                    adapter.bindArticles(model.query.pages)
                    tv_no_result.visibility = View.GONE
                    results.visibility = View.VISIBLE
                } else {
                    tv_no_result.visibility = View.VISIBLE
                    results.visibility = View.GONE
                }
            })
    }

    private fun setupView() {
        adapter = SearchResultAdapter(this, emptyList())
        query_input.requestFocus()

        results.adapter = adapter
        results.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}