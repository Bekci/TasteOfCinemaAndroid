package com.bekci.tasteofcinema.contracts

import com.bekci.tasteofcinema.model.OmdbFullFilm
import com.bekci.tasteofcinema.model.OmdbSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbInterface {

    @GET("https://www.omdbapi.com/")
    fun getSearchResults(
        @Query("apikey") APIkey: String?,
        @Query("s") toSearch: String?
    ): Call<OmdbSearchResult?>?

    @GET("https://www.omdbapi.com/")
    fun getFilm(@Query("apikey") APIkey: String?, @Query("i") filmID: String?): Call<OmdbFullFilm?>?
}