package com.bekci.tasteofcinema.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.singlefilm.SingleFilmFragment
import kotlin.math.sin

class FilmSliderAdapter(fm: FragmentManager, films: MutableList<Film>) : FragmentStatePagerAdapter(fm) {

    private val filmList: MutableList<Film>  = films

    override fun getCount(): Int = filmList.size


    override fun getItem(position: Int): Fragment {
        val singleFilmFragment = SingleFilmFragment()
        val bundle = Bundle()
        bundle.putParcelable("pageFilm", filmList[position])
        singleFilmFragment.arguments = bundle

        return singleFilmFragment
    }

    fun addNewFilms(newFilms: List<Film>){
        filmList.addAll(newFilms)
        notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any): Int {
        if(filmList.contains(`object`)) return filmList.indexOf(`object`)
        else return POSITION_NONE
    }
}