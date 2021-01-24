package com.bekci.tasteofcinema.singlefilm

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.contracts.DialogContract
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.OmdbFullFilm
import com.bekci.tasteofcinema.searchfilm.SearchFilmDialog
import com.bekci.tasteofcinema.util.AlertDialogUtil
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SingleFilmFragment : Fragment(), SingleFilmContract.View, DialogContract{

    private var presenter: SingleFilmContract.Presenter? = null
    private var filmObj : Film? = null

    private lateinit var filmImage : ImageView
    private lateinit var filmTitle : TextView
    private lateinit var filmDetail : TextView
    private lateinit var floatButton : FloatingActionButton

    companion object{
        const val TAG = "SingleFilm"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_singefilm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            filmObj = it.getParcelable("pageFilm")
        }
        setPresenter(SingleFilmPresenter(this))
        init(view)
    }

    private fun init(view : View){
        filmDetail = view.findViewById(R.id.frag_singlefilm_tv_detail)
        filmTitle  = view.findViewById(R.id.frag_singlefilm_tv_title)
        filmImage = view.findViewById(R.id.frag_singlefilm_iv_filmImage)
        floatButton = view.findViewById(R.id.frag_singlefilm_fab)

        filmObj?.let { it ->
            filmDetail.text = it.detail
            filmTitle.text = it.title
            val posterUrl = if (it.imgURL!!.contains("https")) it.imgURL else it.imgURL?.replace("http", "https")
            activity?.let { itC ->
                Glide.with(itC)
                    .load(posterUrl)
                    .placeholder(ColorDrawable(Color.BLACK))
                    .into(filmImage)
            }
        }

        floatButton.setOnClickListener {
            context?.let {
                val cleanedTitle = presenter?.cleanFilmTitle(filmObj)
                cleanedTitle?.let {
                    openSearchDialog(it)
                }
            }
        }
    }

    override fun filmExists() {
        activity?.runOnUiThread {
            context?.let {
                AlertDialogUtil.openAlertDialog(it,
                    it.resources.getString(R.string.err_database_item_found),
                    "Ok", null, {}, {}, true
                )
            }
        }
    }

    override fun filmAdded() {
        activity?.runOnUiThread {
            context?.let {
                AlertDialogUtil.openAlertDialog(it,
                    it.resources.getString(R.string.ok_film_added),
                    "Ok", null, {}, {}, true
                )
            }
        }
    }

    override fun setPresenter(presenter: SingleFilmContract.Presenter) {
        this.presenter = presenter
    }

    private fun openSearchDialog(initialSearchName : String) {
        activity?.let {
            val searchFilmDialog = SearchFilmDialog()
            searchFilmDialog.setTargetFragment(this)
            val bundle = Bundle()
            bundle.putString("initialSearchStr", initialSearchName)
            searchFilmDialog.arguments = bundle
            searchFilmDialog.show(it.supportFragmentManager, SearchFilmDialog.TAG)
        }
    }

    override fun passSelectedFilm(selectedFilm: OmdbFullFilm) {
        context?.let {
            presenter?.saveFilmDatabase(selectedFilm, filmObj?.detail, it)
        }
    }
}
