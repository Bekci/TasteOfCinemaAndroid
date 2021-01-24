package com.bekci.tasteofcinema.util

import androidx.annotation.WorkerThread
import com.bekci.tasteofcinema.contracts.FilmDAO
import com.bekci.tasteofcinema.model.FilmDBO

class DatabaseRepository(private val filmDAO: FilmDAO) {

    val allSavedFilms : List<FilmDBO> = filmDAO.getAllFilms()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(filmDBO: FilmDBO){
        filmDAO.insertFilm(filmDBO)
    }
}