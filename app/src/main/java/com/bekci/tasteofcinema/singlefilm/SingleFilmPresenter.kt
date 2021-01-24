package com.bekci.tasteofcinema.singlefilm

import android.content.Context
import android.os.AsyncTask
import com.bekci.tasteofcinema.FilmDatabaseHelper
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.FilmDBO
import com.bekci.tasteofcinema.model.OmdbFullFilm
import com.bekci.tasteofcinema.util.AlertDialogUtil

class SingleFilmPresenter(view: SingleFilmContract.View) : SingleFilmContract.Presenter {
    private var view : SingleFilmContract.View? = view

    private fun cleanTitle(filmTitle: String) : String{

        // Firstly remove year info in the text. Year info is assumed to be in the form (1992)
        var toReturn = filmTitle.replace("\\(\\d\\d\\d\\d\\)".toRegex(), "")
        // Remove index of the film
        toReturn = toReturn.replace("\\d\\d\\.".toRegex(), "")
        // Remove index of the film
        toReturn = toReturn.replace("\\d\\.".toRegex(), "")
        // Remove space character in the start and end of the string
        if(toReturn[0] == ' ')
            toReturn = toReturn.subSequence(1, toReturn.length).toString()
        if(toReturn[toReturn.length - 1] == ' ')
            toReturn = toReturn.subSequence(0, toReturn.length - 1).toString()

        return toReturn
    }
    override fun cleanFilmTitle(film: Film?) : String?{
        film?.let {
            if (it.title != null){
                return cleanTitle(it.title!!)
            }
        }
        return null
    }

    override fun saveFilmDatabase(selectedFilm: OmdbFullFilm, filmDetail : String?, context : Context) {
        val rating = if (selectedFilm.getImdbRating() == null) -1.0f  else selectedFilm.getImdbRating()!!.toFloat()

        val filmDBO = FilmDBO(selectedFilm.getImdbID() ?: "", selectedFilm.getTitle() ?: "", filmDetail ?: "",
            selectedFilm.getYear()?.toInt() ?: 1900, selectedFilm.getPoster() ?: "", rating)

        AsyncTask.execute {
            val savedFilmSameID = FilmDatabaseHelper.getDatabase(context).filmDao().getFilmsByImdbId(filmDBO.orgId)
            if (savedFilmSameID.isEmpty()){
                FilmDatabaseHelper.getDatabase(context).filmDao().insertFilm(filmDBO)
                view?.filmAdded()
            }
            else  view?.filmExists()
        }

    }

    override fun onStart() {}
    override fun onDestroy() {}
}