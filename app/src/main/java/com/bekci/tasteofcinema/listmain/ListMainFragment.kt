package com.bekci.tasteofcinema.listmain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.model.ListMainInfo
import com.bekci.tasteofcinema.util.WebSiteParser

class ListMainFragment: Fragment() {

    private lateinit var listTitle : TextView
    private lateinit var listDetail : TextView
    private lateinit var listImg : ImageView

    private var listMainObj : ListMainInfo? = null

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
        init(view)
    }

    private fun init(view : View){
        listTitle = view.findViewById(R.id.frag_listmain_tv_title)
        listDetail = view.findViewById(R.id.frag_listmain_tv_detail)
        listImg = view.findViewById(R.id.frag_listmain_iv_listimg)

        listMainObj?.let {
            listTitle.text = it.title
            listDetail.text = it.detail
            WebSiteParser.parseListContent(it)
        }


    }
}