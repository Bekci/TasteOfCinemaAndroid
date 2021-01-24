package com.bekci.tasteofcinema.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.contracts.RecyclerClickInterface
import com.bekci.tasteofcinema.adapter.HomePageListRecyclerAdapter
import com.bekci.tasteofcinema.listmain.ListMainFragment
import com.bekci.tasteofcinema.model.ListMainInfo
import com.bekci.tasteofcinema.savedfilms.SavedFilmsFragment
import com.bekci.tasteofcinema.util.ActivityUtil
import com.bekci.tasteofcinema.util.AlertDialogUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePageFragment : Fragment(), HomePageContract.View, RecyclerClickInterface {

    private var presenter : HomePageContract.Presenter? = null
    private lateinit var listRecycler : RecyclerView
    private var isRecyclerInit : Boolean = false
    private var homePageListRecyclerAdapter : HomePageListRecyclerAdapter? = null
    private lateinit var progressCardView : CardView
    private lateinit var savedFilmFab: FloatingActionButton

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

        if(presenter == null)
            setPresenter(HomePagePresenter(this))

        // When back from back button, fetch the first page
        if(isRecyclerInit)
            presenter?.resetNumFetchedPages()

        init(view)


        progressCardView.visibility = View.VISIBLE
        presenter?.fetchLists()
    }

    private fun init(view : View){
        listRecycler = view.findViewById(R.id.frag_home_rv_lists)
        progressCardView = view.findViewById(R.id.frag_home_cv_progress)
        savedFilmFab = view.findViewById(R.id.frag_home_fab)

        progressCardView.visibility = View.INVISIBLE
        listRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        context?.let {
            homePageListRecyclerAdapter = HomePageListRecyclerAdapter(mutableListOf(), this, it)
        }

        listRecycler.adapter = homePageListRecyclerAdapter
        isRecyclerInit = true

        listRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            // Check the list reached to the end
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1)){
                    progressCardView.visibility = View.VISIBLE
                    presenter?.fetchLists()
                }
            }
        })

        savedFilmFab.setOnClickListener {
            activity?.let {
                ActivityUtil.changeFragment(SavedFilmsFragment(), SavedFilmsFragment.TAG,
                    it.supportFragmentManager, R.id.act_main_fl_container, true)
            }

        }

    }

    override fun onListFetched(listList: List<ListMainInfo>) {
        homePageListRecyclerAdapter?.addNewLists(listList)
        progressCardView.visibility = View.INVISIBLE
    }

    override fun onListCannotFetched() {
        activity?.runOnUiThread {
            context?.let {  activity?.let { ait -> AlertDialogUtil.openNoInternetDialog(it, ait) }}
        }

    }

    override fun setPresenter(presenter: HomePageContract.Presenter) {
        this.presenter = presenter
    }

    override fun onItemClicked(listItem: ListMainInfo) {
        val listMainFrag = ListMainFragment()
        val bundle = Bundle()
        bundle.putParcelable("listMainObj", listItem)
        listMainFrag.arguments = bundle

        activity?.let {
            ActivityUtil.changeFragment(listMainFrag, ListMainFragment.TAG,
                it.supportFragmentManager, R.id.act_main_fl_container, true)

        }
    }
}