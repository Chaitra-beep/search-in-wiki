package com.example.searchinwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchinwiki.model.SearchResponse
import com.example.searchinwiki.service.APIService
import com.example.searchinwiki.service.ServiceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Chaitra on 22,June,2021
 */
class ArticleViewModel : ViewModel() {

    private val data: MutableLiveData<SearchResponse> = MutableLiveData()

    fun getDataObserver(): MutableLiveData<SearchResponse> {
        return data
    }

    fun makeApiCall(query: String){
        val apiService: APIService = ServiceManager.getRetroClient()!!.create(APIService::class.java)
        val call: Call<SearchResponse> = apiService.getArticles(query)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable?) {
                data.postValue(null)
            }
        })
    }


}