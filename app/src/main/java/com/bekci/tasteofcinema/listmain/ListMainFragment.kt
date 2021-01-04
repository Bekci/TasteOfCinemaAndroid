package com.bekci.tasteofcinema.listmain

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.ListViewCompat
import androidx.fragment.app.Fragment
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.SingleFilmPagerActivity
import com.bekci.tasteofcinema.home.HomePagePresenter
import com.bekci.tasteofcinema.model.ListContent
import com.bekci.tasteofcinema.model.ListMainInfo
import com.bekci.tasteofcinema.util.ActivityUtil
import com.bekci.tasteofcinema.util.WebSiteParser
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListMainFragment: Fragment(), ListMainContract.View {

    private lateinit var listTitle : TextView
    private lateinit var listDetail : TextView
    private lateinit var listImg : ImageView
    private lateinit var progressCard : CardView
    private lateinit var floatingPointStart : FloatingActionButton


    private var listMainObj : ListMainInfo? = null
    private var presenter : ListMainContract.Presenter? = null
    private var listContent : ListContent? = null

    companion object{
        const val TAG = "ListMainFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_listmainpage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            listMainObj = it.getParcelable("listMainObj")
        }

        if(presenter == null)
            setPresenter(ListMainPresenter(this))

        init(view)
    }

    private fun init(view : View){
        listTitle = view.findViewById(R.id.frag_listmain_tv_title)
        listDetail = view.findViewById(R.id.frag_listmain_tv_detail)
        listImg = view.findViewById(R.id.frag_listmain_iv_listimg)
        progressCard = view.findViewById(R.id.frag_listmain_cv_progress)
        floatingPointStart = view.findViewById(R.id.frag_listmain_fab_starttour)

        listMainObj?.let {
            listTitle.text = it.title
            listDetail.text = it.detail

            if (this.listContent == null){
                progressCard.visibility = View.VISIBLE
                presenter?.fetchListContent(it)
            }
            else{
                setListDetail(this.listContent!!)
            }
        }

        floatingPointStart.setOnClickListener {
            startTour()
        }

        context?.let {
            Glide.with(it).load(listMainObj?.imgURL).into(listImg)
        }

    }

    override fun onListContentFetched(listContent: ListContent) {
        setListDetail(listContent)
    }

    private fun setListDetail(listContent: ListContent){
        listDetail.text = listContent.detail
        progressCard.visibility = View.INVISIBLE
        this.listContent = listContent
    }


    override fun setPresenter(presenter: ListMainContract.Presenter) {
        this.presenter = presenter
    }

    private fun startTour(){
        val sliderFragment = SingleFilmPagerActivity()
        val bundle = Bundle()
        bundle.putParcelable("listContent", listContent)
        sliderFragment.arguments = bundle

        activity?.let {
            ActivityUtil.changeFragment(sliderFragment, SingleFilmPagerActivity.TAG,
                it.supportFragmentManager, R.id.act_main_fl_container, true)
        }
    }
}