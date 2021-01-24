package com.bekci.tasteofcinema.model

import com.google.gson.annotations.SerializedName

class OmdbFullFilm {
    @SerializedName("Title")
    private var Title: String? = null

    @SerializedName("Year")
    private var Year: String? = null

    @SerializedName("Rated")
    private var Rated: String? = null

    @SerializedName("Released")
    private var Released: String? = null

    @SerializedName("Runtime")
    private var Runtime: String? = null

    @SerializedName("Genre")
    private var Genre: String? = null

    @SerializedName("Director")
    private var Director: String? = null

    @SerializedName("Writer")
    private var Writer: String? = null

    @SerializedName("Actors")
    private var Actors: String? = null

    @SerializedName("Plot")
    private var Plot: String? = null

    @SerializedName("Language")
    private var Language: String? = null

    @SerializedName("Country")
    private var Country: String? = null

    @SerializedName("Awards")
    private var Awards: String? = null

    @SerializedName("Poster")
    private var Poster: String? = null

    @SerializedName("Metascore")
    private var Metascore: String? = null

    @SerializedName("imdbRating")
    private var imdbRating: String? = null

    @SerializedName("imdbVotes")
    private var imdbVotes: String? = null

    @SerializedName("imdbID")
    private var imdbID: String? = null

    @SerializedName("Type")
    private var Type: String? = null

    @SerializedName("DVD")
    private var DVD: String? = null

    @SerializedName("BoxOffice")
    private var BoxOffice: String? = null

    @SerializedName("Production")
    private var Production: String? = null

    @SerializedName("Website")
    private var Website: String? = null

    @SerializedName("Response")
    private var Response: String? = null

    @SerializedName("Ratings")
    private var Ratings: List<OmdbRatings>? = null

    fun setTitle(title: String?) {
        Title = title
    }

    fun setYear(year: String?) {
        Year = year
    }

    fun setRated(rated: String?) {
        Rated = rated
    }

    fun setReleased(released: String?) {
        Released = released
    }

    fun setRuntime(runtime: String?) {
        Runtime = runtime
    }

    fun setGenre(genre: String?) {
        Genre = genre
    }

    fun setDirector(director: String?) {
        Director = director
    }

    fun setWriter(writer: String?) {
        Writer = writer
    }

    fun setActors(actors: String?) {
        Actors = actors
    }

    fun setPlot(plot: String?) {
        Plot = plot
    }

    fun setLanguage(language: String?) {
        Language = language
    }

    fun setCountry(country: String?) {
        Country = country
    }

    fun setAwards(awards: String?) {
        Awards = awards
    }

    fun setPoster(poster: String?) {
        Poster = poster
    }

    fun setMetascore(metascore: String?) {
        Metascore = metascore
    }

    fun setImdbRating(imdbRating: String?) {
        this.imdbRating = imdbRating
    }

    fun setImdbVotes(imdbVotes: String?) {
        this.imdbVotes = imdbVotes
    }

    fun setImdbID(imdbID: String?) {
        this.imdbID = imdbID
    }

    fun setType(type: String?) {
        Type = type
    }

    fun setDVD(DVD: String?) {
        this.DVD = DVD
    }

    fun setBoxOffice(boxOffice: String?) {
        BoxOffice = boxOffice
    }

    fun setProduction(production: String?) {
        Production = production
    }

    fun setWebsite(website: String?) {
        Website = website
    }

    fun setResponse(response: String?) {
        Response = response
    }

    fun setRatings(ratings: List<OmdbRatings>?) {
        Ratings = ratings
    }

    fun getTitle(): String? {
        return Title
    }

    fun getYear(): String? {
        return Year
    }

    fun getRated(): String? {
        return Rated
    }

    fun getReleased(): String? {
        return Released
    }

    fun getRuntime(): String? {
        return Runtime
    }

    fun getGenre(): String? {
        return Genre
    }

    fun getDirector(): String? {
        return Director
    }

    fun getWriter(): String? {
        return Writer
    }

    fun getActors(): String? {
        return Actors
    }

    fun getPlot(): String? {
        return Plot
    }

    fun getLanguage(): String? {
        return Language
    }

    fun getCountry(): String? {
        return Country
    }

    fun getAwards(): String? {
        return Awards
    }

    fun getPoster(): String? {
        return Poster
    }

    fun getMetascore(): String? {
        return Metascore
    }

    fun getImdbRating(): String? {
        return imdbRating
    }

    fun getImdbVotes(): String? {
        return imdbVotes
    }

    fun getImdbID(): String? {
        return imdbID
    }

    fun getType(): String? {
        return Type
    }

    fun getDVD(): String? {
        return DVD
    }

    fun getBoxOffice(): String? {
        return BoxOffice
    }

    fun getProduction(): String? {
        return Production
    }

    fun getWebsite(): String? {
        return Website
    }

    fun getResponse(): String? {
        return Response
    }

    fun getRatings(): List<OmdbRatings>? {
        return Ratings
    }
}