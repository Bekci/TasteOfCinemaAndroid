package com.bekci.tasteofcinema.searchfilm;

import android.content.Context
import android.util.Log
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.contracts.OmdbInterface
import com.bekci.tasteofcinema.model.OmdbFullFilm
import com.bekci.tasteofcinema.model.OmdbSearchResult
import com.bekci.tasteofcinema.util.ClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFilmPresenter(view : SearchFilmContract.View) : SearchFilmContract.Presenter{

    private val inView : SearchFilmContract.View = view

    override fun searchFilms(context: Context, searchStr : String) {
        val omdbResult = ClientInstance.getInstance()?.create(OmdbInterface::class.java)

        val call = omdbResult?.getSearchResults(context.getString(R.string.omdbapi), searchStr)
        call?.enqueue(object : Callback<OmdbSearchResult?> {
            override fun onResponse(call: Call<OmdbSearchResult?>, response: Response<OmdbSearchResult?>)
            {
                if (response.isSuccessful){
                    val foundFilms = response.body()?.getSearch()
                    foundFilms?.let {
                        inView.onFilmsFetched(it)
                    }
                }
            }
            override fun onFailure(call: Call<OmdbSearchResult?>, t: Throwable) {
                Log.e(SearchFilmDialog.TAG, "err")
            }
        })
    }

    override fun fetchFilm(context: Context, filmId: String) {
        val omdbResult = ClientInstance.getInstance()?.create(OmdbInterface::class.java)
        val call = omdbResult?.getFilm(context.getString(R.string.omdbapi), filmId)
        call?.enqueue(object : Callback<OmdbFullFilm?> {
            override fun onResponse(call: Call<OmdbFullFilm?>, response: Response<OmdbFullFilm?>)
            {
                if (response.isSuccessful){
                    val foundFilms = response.body()
                    foundFilms?.let {
                        inView.onFilmFetched(it)
                    }
                }
            }
            override fun onFailure(call: Call<OmdbFullFilm?>, t: Throwable) {
                Log.e(SearchFilmDialog.TAG, "err")
            }
        })
    }


    override fun onStart() {}
    override fun onDestroy() {}
}
