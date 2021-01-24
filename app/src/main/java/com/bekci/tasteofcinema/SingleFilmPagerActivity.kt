package com.bekci.tasteofcinema

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bekci.tasteofcinema.contracts.ParserInterface
import com.bekci.tasteofcinema.adapter.FilmSliderAdapter
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.ListContent
import com.bekci.tasteofcinema.model.ListMainInfo
import com.bekci.tasteofcinema.util.WebSiteParser

class SingleFilmPagerActivity : Fragment(), ParserInterface {

    companion object{
        const val TAG = "SingleFilmSlider"
    }
    private lateinit var viewpager: ViewPager
    private lateinit var filmSliderAdapter: FilmSliderAdapter
    private var fetchedListContent: ListContent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            fetchedListContent = it.getParcelable("listContent")
        }
        init(view)
    }

    private fun init(view: View){
        viewpager = view.findViewById(R.id.listfrags_viewpager)
        fetchedListContent?.let {
            filmSliderAdapter = FilmSliderAdapter(childFragmentManager, it.films)
            viewpager.adapter = filmSliderAdapter
            // Fetch all pages of the list
            val listMainInfo = ListMainInfo()
            listMainInfo.title = it.title
            listMainInfo.detail = it.detail
            listMainInfo.date = it.date
            for (i in 2..(it.numPages) ){
                WebSiteParser.parseListPage(listMainInfo, i, this)
            }
        }
    }
    override fun onListsParsed(listLists: List<ListMainInfo>) {}
    override fun onListContentParsed(listContent: ListContent) {}

    override fun onListPageParsed(listFilms: List<Film>) {
        if(listFilms.isNotEmpty())
            filmSliderAdapter.addNewFilms(listFilms)
    }

    override fun onRequestFailed() {

    }
}