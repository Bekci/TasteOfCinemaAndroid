package com.bekci.tasteofcinema.contracts

import androidx.room.*
import com.bekci.tasteofcinema.model.FilmDBO

@Dao
interface FilmDAO {

    @Query("Select * from film")
    fun getAllFilms() : List<FilmDBO>
    @Query("Select * from film where orgId LIKE :orgId")
    fun getFilmsByImdbId(orgId: String) : List<FilmDBO>
    @Insert
    fun insertFilm(filmDBO: FilmDBO)
    @Update
    fun updateFilm(filmDBO: FilmDBO)
    @Delete
    fun deleteFilm(filmDBO: FilmDBO)

}