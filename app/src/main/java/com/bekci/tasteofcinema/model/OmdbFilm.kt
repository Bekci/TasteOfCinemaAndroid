package com.bekci.tasteofcinema.model

import com.google.gson.annotations.SerializedName

class OmdbFilm {

    @SerializedName("Title")
    private var Title: String? = null
    @SerializedName("Year")
    private var Year: String? = null
    @SerializedName("imdbID")
    private var imdbID: String? = null
    @SerializedName("Type")
    private var Type: String? = null
    @SerializedName("Poster")
    private var Poster: String? = null

    fun FilmsInSearchResults(
        Title: String?,
        Year: String?,
        imdbID: String?,
        Type: String?,
        Poster: String?
    ) {
        this.Title = Title
        this.Year = Year
        this.imdbID = imdbID
        this.Type = Type
        this.Poster = Poster
    }

    fun getTitle(): String? {
        return Title
    }

    fun getYear(): String? {
        return Year
    }

    fun getImdbID(): String? {
        return imdbID
    }

    fun getType(): String? {
        return Type
    }

    fun getPoster(): String? {
        return Poster
    }

    fun setTitle(Title: String?) {
        this.Title = Title
    }

    fun setYear(year: String?) {
        Year = year
    }

    fun setType(type: String?) {
        Type = type
    }

    fun setPoster(poster: String?) {
        Poster = poster
    }

    fun setImdbID(imdbID: String?) {
        this.imdbID = imdbID
    }
}