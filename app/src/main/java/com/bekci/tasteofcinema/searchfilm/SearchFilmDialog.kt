package com.bekci.tasteofcinema.searchfilm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.contracts.DialogContract
import com.bekci.tasteofcinema.contracts.DialogRecyclerInterface
import com.bekci.tasteofcinema.adapter.SearchedFilmsRecyclerAdapter
import com.bekci.tasteofcinema.model.OmdbFilm
import com.bekci.tasteofcinema.model.OmdbFullFilm

class SearchFilmDialog : DialogFragment(), SearchFilmContract.View, DialogRecyclerInterface {

    companion object{
        const val TAG = "Search_Film_Dialog"
    }

    private var targetFragment : DialogContract? = null
    private lateinit var filmNameEdit : EditText
    private lateinit var dialogCancel : Button
    private lateinit var dialogDone : Button
    private lateinit var buttonSearch : Button
    private lateinit var filmsRecycler: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var searchedFilmsAdapter : SearchedFilmsRecyclerAdapter? = null

    private var searchStr : String? = ""
    private var presenter : SearchFilmContract.Presenter? = null
    private var selectedSearchedFilm : OmdbFilm? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_imdb_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            searchStr = it.getString("initialSearchStr")
        }
        setPresenter(SearchFilmPresenter(this))
        init(view)
    }

    private fun init(view: View){

        filmNameEdit = view.findViewById(R.id.frag_imdbsearch_et_filmName)
        dialogCancel  = view.findViewById(R.id.frag_imdbsearch_button_cancel)
        dialogDone = view.findViewById(R.id.frag_imdbsearch_button_done)
        buttonSearch = view.findViewById(R.id.frag_imdbsearch_button_search)
        progressBar = view.findViewById(R.id.frag_imdbsearch_pb_progress)
        filmsRecycler = view.findViewById(R.id.frag_imdbsearch_rv_films)

        filmsRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        filmNameEdit.setText(searchStr)

        dialogCancel.setOnClickListener { dialog?.dismiss() }
        context?.let {
            searchedFilmsAdapter = SearchedFilmsRecyclerAdapter(mutableListOf(), this, it)
        }
        filmsRecycler.adapter = searchedFilmsAdapter

        buttonSearch.setOnClickListener {  searchFilms() }
        dialogDone.setOnClickListener {
            fetchFilm()
        }
        // Finally search films with initial string
        searchFilms()
    }

    private fun searchFilms() {
        progressBar.visibility = View.VISIBLE
        filmsRecycler.visibility = View.INVISIBLE

        val searchStr = filmNameEdit.text.toString()
        if(searchStr.isNotBlank() && context != null){
            presenter?.searchFilms(context!!, searchStr)
        }
    }

    private fun fetchFilm() {
        progressBar.visibility = View.VISIBLE
        filmsRecycler.visibility = View.INVISIBLE

        if(selectedSearchedFilm != null && context != null && selectedSearchedFilm!!.getImdbID() != null){
            presenter?.fetchFilm(context!!, selectedSearchedFilm!!.getImdbID()!!)
        }
    }


    override fun onFilmsFetched(savedFilms: List<OmdbFilm?>) {
        progressBar.visibility = View.INVISIBLE
        filmsRecycler.visibility = View.VISIBLE

        searchedFilmsAdapter?.addFilms(savedFilms)
    }

    override fun onFilmFetched(film: OmdbFullFilm) {
        progressBar.visibility = View.INVISIBLE
        filmsRecycler.visibility = View.VISIBLE
        targetFragment?.passSelectedFilm(film)
        dialog?.dismiss()
    }

    override fun setPresenter(presenter: SearchFilmContract.Presenter) {
        this.presenter = presenter
    }

    override fun onDialogRecyclerItemClicked(clickedItem: OmdbFilm) {
        selectedSearchedFilm = clickedItem
    }

    fun setTargetFragment(contract : DialogContract){
        targetFragment = contract
    }
}