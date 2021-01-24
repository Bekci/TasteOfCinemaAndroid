package com.bekci.tasteofcinema.savedfilms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.adapter.HomePageListRecyclerAdapter
import com.bekci.tasteofcinema.adapter.SavedFilmListAdapter
import com.bekci.tasteofcinema.adapter.SearchedFilmsRecyclerAdapter
import com.bekci.tasteofcinema.contracts.SavedFilmRecyclerClickInterface
import com.bekci.tasteofcinema.home.HomePageContract
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.FilmDBO
import com.bekci.tasteofcinema.singlefilm.SingleFilmFragment
import com.bekci.tasteofcinema.util.ActivityUtil
import com.bekci.tasteofcinema.util.AlertDialogUtil

class SavedFilmsFragment : Fragment(), SavedFilmContract.View, SavedFilmRecyclerClickInterface {

    private var presenter : SavedFilmContract.Presenter? = null
    private lateinit var listRecycler : RecyclerView
    private var isRecyclerInit : Boolean = false
    private var savedFilmAdapter : SavedFilmListAdapter? = null
    private lateinit var progressCardView : CardView

    companion object{
        const val TAG  = "SavedFilms"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_savedfilm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(presenter == null)
            setPresenter(SavedFilmsPresenter(this))

        init(view)

        context?.let {
            presenter?.fetchFilms(it)
        }

    }

    private fun init(view : View) {
        progressCardView = view.findViewById(R.id.frag_savedfilm_cv_progress)
        progressCardView.visibility = View.INVISIBLE
        listRecycler = view.findViewById(R.id.frag_savedfilm_rv_lists)
        listRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        context?.let {
            savedFilmAdapter = SavedFilmListAdapter(mutableListOf(), this, it)
            listRecycler.adapter = savedFilmAdapter
        }
    }

    override fun onFilmsFetched(savedFilms: List<FilmDBO>) {
        activity?.runOnUiThread {
            savedFilmAdapter?.addNewItems(savedFilms)
            savedFilmAdapter?.notifyDataSetChanged()
        }
    }

    override fun onFilmsDeleted(itemPos: Int) {
        activity?.runOnUiThread {
            savedFilmAdapter?.removeItemAt(itemPos)
            savedFilmAdapter?.notifyItemRemoved(itemPos)
            savedFilmAdapter?.notifyItemRangeChanged(itemPos, savedFilmAdapter!!.getListSize())
        }
    }

    override fun setPresenter(presenter: SavedFilmContract.Presenter) {
        this.presenter = presenter
    }

    override fun onItemClicked(selectedFilm: FilmDBO) {
        openFilmPage(selectedFilm)
    }

    override fun onItemLongClicked(selectedFilm: FilmDBO, pos: Int): Boolean {
        context?.let {
            AlertDialogUtil.openAlertDialog(it, "Do you want to remove this film?"
                , "Yes", "No", {presenter?.deleteSavedFilm(it , selectedFilm, pos)}, {}, true)
        }
        return true
    }


    private fun openFilmPage(selectedFilm: FilmDBO) {
        val film = Film()
        film.detail = selectedFilm.detail
        film.imgURL = selectedFilm.posterURL
        film.title = selectedFilm.title
        film.year = selectedFilm.year
        film.imdbID = selectedFilm.orgId

        val singleFilmFragment = SingleFilmFragment()
        val bundle = Bundle()
        bundle.putParcelable("pageFilm", film)
        singleFilmFragment.arguments = bundle

        activity?.let {
            ActivityUtil.changeFragment(singleFilmFragment, SingleFilmFragment.TAG,
                it.supportFragmentManager, R.id.act_main_fl_container, true)
        }
    }
}