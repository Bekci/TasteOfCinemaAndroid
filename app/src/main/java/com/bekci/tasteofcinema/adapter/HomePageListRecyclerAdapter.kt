package com.bekci.tasteofcinema.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bekci.tasteofcinema.R
import kotlinx.android.synthetic.main.lists_list_item.view.*

class HomePageListRecyclerAdapter : RecyclerView.Adapter<HomePageListRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lists_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }

    class  ViewHolder : RecyclerView.ViewHolder{
        var listImage: ImageView
        var listTitle: TextView
        var listDetail : TextView

        constructor(itemView: View) : super(itemView) {
            listDetail = itemView.findViewById(R.id.item_lists_tv_detail)
            listTitle = itemView.findViewById(R.id.item_lists_tv_title)
            listImage = itemView.findViewById(R.id.item_lists_cv_image)
        }
    }
}