package com.bekci.tasteofcinema.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bekci.tasteofcinema.R

class HomePageFragment : Fragment(), HomePageContract.View {

    private var presenter : HomePageContract.Presenter? = null
    private lateinit var listRecycler : RecyclerView
    companion object{
        const val TAG = "HomePageFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPresenter(HomePagePresenter(this))
        init(view)
        presenter?.fetchLists()
    }

    private fun init(view : View){
        listRecycler = view.findViewById(R.id.frag_home_rv_lists)
        listRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onListFetched() {
        // TODO: Update recycle view
    }

    override fun setPresenter(presenter: HomePageContract.Presenter) {
        this.presenter = presenter
    }
}