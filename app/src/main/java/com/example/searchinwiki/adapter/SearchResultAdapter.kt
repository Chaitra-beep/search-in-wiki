package com.example.searchinwiki.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchinwiki.cardview.SearchResultCardView
import com.example.searchinwiki.model.Page

/**
 * Created by Chaitra on 22,June,2021
 */
class SearchResultAdapter(con: Context, items: List<Page>?) :
    RecyclerView.Adapter<SearchResultAdapter.ListItemViewHolder>() {
    var context = con
    private var itemList = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val child = SearchResultCardView(context)
        child.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ListItemViewHolder(child)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val smallerNewsCard: SearchResultCardView = holder.itemView as SearchResultCardView
        smallerNewsCard.bindData(itemList?.get(position))
    }

    override fun getItemCount(): Int {
        return itemList?.size!!
    }

    fun bindArticles(data: List<Page>) {
        itemList = data
        notifyDataSetChanged()
    }


    class ListItemViewHolder(var mView: View) :
        RecyclerView.ViewHolder(mView)
}