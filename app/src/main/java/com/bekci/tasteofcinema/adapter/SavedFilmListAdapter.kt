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
import com.bekci.tasteofcinema.contracts.SavedFilmRecyclerClickInterface
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.FilmDBO
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SavedFilmListAdapter(recyclerList: List<FilmDBO>, clickInterface: SavedFilmRecyclerClickInterface,
                           context : Context) : RecyclerView.Adapter<SavedFilmListAdapter.ViewHolder>() {

    private var listLists : MutableList<FilmDBO> = mutableListOf()
    private var clickListener: SavedFilmRecyclerClickInterface = clickInterface
    private var aContext : Context = context

    init {
        listLists.addAll(recyclerList)
    }

    fun addNewItems(newItems: List<FilmDBO>){
        listLists.addAll(newItems)
    }

    fun removeItemAt(itemPos: Int) {
        listLists.removeAt(itemPos)

    }
    fun getListSize() : Int = listLists.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var listImage: ImageView = itemView.findViewById(R.id.item_saved_films_cv_image)
        var listTitle: TextView = itemView.findViewById(R.id.item_saved_films_tv_title)
        var listDetail : TextView = itemView.findViewById(R.id.item_saved_films_tv_detail)
        var listContainer: ConstraintLayout = itemView.findViewById(R.id.item_saved_films_container)
        var listRating: TextView = itemView.findViewById(R.id.item_saved_films_tv_rating)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedFilmListAdapter.ViewHolder {
        return SavedFilmListAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.lists_saved_films, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listLists.size
    }

    override fun onBindViewHolder(holder: SavedFilmListAdapter.ViewHolder, position: Int) {
        val item = listLists[position]
        holder.listTitle.text = item.title
        holder.listDetail.text = item.detail
        Glide.with(aContext)
            .load(item.posterURL)
            .fitCenter()
            .into(holder.listImage)
        holder.listContainer.setOnClickListener { clickListener.onItemClicked(item) }
        holder.listContainer.setOnLongClickListener { clickListener.onItemLongClicked(item, position) }
        holder.listRating.text = "${item.rating}"
    }
}