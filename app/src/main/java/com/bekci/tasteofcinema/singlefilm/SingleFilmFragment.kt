package com.bekci.tasteofcinema.singlefilm

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.ListContent
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SingleFilmFragment : Fragment(), SingleFilmContract.View{

    private var presenter: SingleFilmContract.Presenter? = null
    private var filmObj : Film? = null

    private lateinit var filmImage : ImageView
    private lateinit var filmTitle : TextView
    private lateinit var filmDetail : TextView

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
        setPresenter(SingleFilmPresenter())
        init(view)
    }

    private fun init(view : View){
        filmDetail = view.findViewById(R.id.frag_singlefilm_tv_detail)
        filmTitle  = view.findViewById(R.id.frag_singlefilm_tv_title)
        filmImage = view.findViewById(R.id.frag_singlefilm_iv_filmImage)

        filmObj?.let { it ->
            filmDetail.text = it.detail
            filmTitle.text = it.title
            activity?.let { itC ->
                Glide.with(itC)
                    .load(it.imgURL?.replace("http", "https"))
                    .placeholder(ColorDrawable(Color.BLACK))
                    .into(filmImage)
            }
        }
    }


    override fun setPresenter(presenter: SingleFilmContract.Presenter) {
        this.presenter = presenter
    }
}