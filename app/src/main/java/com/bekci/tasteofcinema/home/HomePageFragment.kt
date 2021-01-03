package com.bekci.tasteofcinema.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.`interface`.RecyclerClickInterface
import com.bekci.tasteofcinema.adapter.HomePageListRecyclerAdapter
import com.bekci.tasteofcinema.listmain.ListMainFragment
import com.bekci.tasteofcinema.model.ListMainInfo
import com.bekci.tasteofcinema.util.ActivityUtil

class HomePageFragment : Fragment(), HomePageContract.View, RecyclerClickInterface {

    private var presenter : HomePageContract.Presenter? = null
    private lateinit var listRecycler : RecyclerView
    private var isRecyclerInit : Boolean = false
    private var homePageListRecyclerAdapter : HomePageListRecyclerAdapter? = null
    private lateinit var progressCardView : CardView

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

        //Show previous content when returned this fragment with back button
        if(homePageListRecyclerAdapter != null){
            listRecycler.adapter = homePageListRecyclerAdapter
        }
        else{
            progressCardView.visibility = View.VISIBLE
            presenter?.fetchLists()
        }
    }

    private fun init(view : View){
        listRecycler = view.findViewById(R.id.frag_home_rv_lists)
        listRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

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
        progressCardView = view.findViewById(R.id.frag_home_cv_progress)
    }

    override fun onListFetched(listList: List<ListMainInfo>) {
        // First time that lists fetched. Init recycler's adapter
        if (!isRecyclerInit){
            homePageListRecyclerAdapter = HomePageListRecyclerAdapter(listList, this)
            listRecycler.adapter = homePageListRecyclerAdapter
        }
        // Add new items to recycler
        else{
            homePageListRecyclerAdapter?.addNewLists(listList)
        }
        progressCardView.visibility = View.GONE

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
            // When returned from another page, below variable stays as true
            // Because of it fetched content did not shown on the page see onListFetched
            isRecyclerInit = false
            ActivityUtil.changeFragment(listMainFrag, ListMainFragment.TAG,
                it.supportFragmentManager, R.id.act_main_fl_container, true)

        }

    }
}