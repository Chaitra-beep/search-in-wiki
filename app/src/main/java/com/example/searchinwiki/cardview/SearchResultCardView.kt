package com.example.searchinwiki.cardview

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.searchinwiki.R
import com.example.searchinwiki.model.Page
import kotlinx.android.synthetic.main.search_result_card_view.view.*

/**
 * Created by Chaitra on 22,June,2021
 */
class SearchResultCardView : LinearLayout {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    var pageId: Int = 0

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.search_result_card_view, this)

        this.setOnClickListener {
            if (this.pageId != 0) {
                val uri = "https://en.m.wikipedia.org/?curid=${pageId}".toUri()
                val intent: Intent = Intent(Intent.ACTION_VIEW).setData(uri)
                context.startActivity(intent)

            }
        }
    }

    fun bindData(article: Page?) {

        Glide.with(context)
            .load(article?.thumbnail?.source)
            .placeholder(R.drawable.wikipedia_logo)
            .apply(RequestOptions.centerCropTransform())
            .into(iv_article_image)


        if (article?.title?.isEmpty() == true) {
            tv_title.visibility = GONE
        } else {
            tv_title.visibility = VISIBLE
            tv_title.text = article?.title
        }

        if (article?.terms?.description?.size == 0) {
            tv_description.visibility = GONE
        } else {
            tv_description.visibility = VISIBLE

            if (article?.terms?.description != null) {
                tv_description.text = article.terms.description[0]
            }
        }

        pageId = article?.pageid!!

    }


}