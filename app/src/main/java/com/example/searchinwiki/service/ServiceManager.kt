package com.example.searchinwiki.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Chaitra on 22,June,2021
 */
class ServiceManager {

    companion object {
        var BASE_URL = "https://en.wikipedia.org/w/" //volley_array.json

        private var retrofit: Retrofit? = null

        fun getRetroClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }


}