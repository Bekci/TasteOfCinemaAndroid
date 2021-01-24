package com.bekci.tasteofcinema.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="film")
data class FilmDBO(@PrimaryKey(autoGenerate = true) var filmId: Int = 0, val orgId: String,
                   val title: String, val detail: String, val year: Int, val posterURL: String, val rating: Float) {

    constructor(imdbID:String, title:String, detail: String, year: Int,
                url: String, rate: Float) : this(filmId=0, orgId=imdbID, title=title, detail=detail, year=year, posterURL=url, rating=rate)
}
