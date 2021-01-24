package com.bekci.tasteofcinema.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ClientInstance {

    companion object{
        private var retrofit: Retrofit? = null
        val BASE_URL = "https://www.omdbapi.com/"

        fun getInstance(): Retrofit? {
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