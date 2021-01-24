package com.bekci.tasteofcinema.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bekci.tasteofcinema.R
import com.bekci.tasteofcinema.contracts.RecyclerClickInterface
import com.bekci.tasteofcinema.model.ListMainInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HomePageListRecyclerAdapter(recyclerList: List<ListMainInfo>, clickInterface: RecyclerClickInterface, context : Context) :
    RecyclerView.Adapter<HomePageListRecyclerAdapter.ViewHolder>() {

    private var listLists : MutableList<ListMainInfo> = mutableListOf()
    private var clickListener: RecyclerClickInterface = clickInterface
    private var aContext : Context = context

    init {
        listLists.addAll(recyclerList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lists_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listLists[position]
        holder.listTitle.text = item.title
        holder.listDetail.text = item.detail
        holder.listContainer.setOnClickListener { clickListener.onItemClicked(item) }
        Glide.with(aContext)
            .load(item.imgURL)
            .apply(RequestOptions().override(230, 105))
            .fitCenter()
            .into(holder.listImage)
    }

    fun addNewLists(newItems: List<ListMainInfo>){
        listLists.addAll(newItems)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return listLists.size
    }

    class  ViewHolder : RecyclerView.ViewHolder{
        var listImage: ImageView
        var listTitle: TextView
        var listDetail : TextView
        var listContainer: ConstraintLayout

        constructor(itemView: View) : super(itemView) {
            listContainer = itemView.findViewById(R.id.item_lists_container)
            listDetail = itemView.findViewById(R.id.item_lists_tv_detail)
            listTitle = itemView.findViewById(R.id.item_lists_tv_title)
            listImage = itemView.findViewById(R.id.item_lists_cv_image)
        }
    }
}