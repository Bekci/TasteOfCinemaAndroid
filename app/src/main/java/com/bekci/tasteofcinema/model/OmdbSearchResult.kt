package com.bekci.tasteofcinema.model

import com.google.gson.annotations.SerializedName

class OmdbSearchResult {
    @SerializedName("Search")
    private var Search: List<OmdbFilm?>? = null

    @SerializedName("totalResults")
    private var totalResults: Int? = null

    @SerializedName("Response")
    private var Response: String? = null


    fun getTotalResults(): Int? {
        return totalResults
    }

    fun getSearch(): List<OmdbFilm?>? {
        return Search
    }

    fun getResponse(): String? {
        return Response
    }

    fun setResponse(response: String?) {
        Response = response
    }

    fun setSearch(search: List<OmdbFilm?>?) {
        Search = search
    }

    fun setTotalResults(totalResults: Int?) {
        this.totalResults = totalResults
    }
}