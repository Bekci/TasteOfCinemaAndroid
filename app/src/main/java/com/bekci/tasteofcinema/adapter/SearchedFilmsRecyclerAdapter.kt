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
import com.bekci.tasteofcinema.contracts.DialogRecyclerInterface
import com.bekci.tasteofcinema.model.OmdbFilm
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SearchedFilmsRecyclerAdapter(recyclerList : MutableList<OmdbFilm?>, clickInterface: DialogRecyclerInterface, context : Context)
    : RecyclerView.Adapter<SearchedFilmsRecyclerAdapter.ViewHolder>(){

    private var listFilms : MutableList<OmdbFilm?> = recyclerList
    private var clickListener: DialogRecyclerInterface = clickInterface
    private var aContext : Context = context
    private var prevClickedIdx = -1
    private var currentClickedIdx = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SearchedFilmsRecyclerAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_search_film, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listFilms[position]
        item?.let {
            if (currentClickedIdx == position) holder.checkIcon.visibility = View.VISIBLE
            else holder.checkIcon.visibility = View.INVISIBLE
            holder.filmTitle.text = "${it.getTitle()}"
            holder.filmScore.text = "${item.getYear()}"
            Glide.with(aContext)
                .load(it.getPoster())
                .apply(RequestOptions().override(330, 175))
                .fitCenter()
                .into(holder.filmImage)

            holder.container.setOnClickListener {
                clickListener.onDialogRecyclerItemClicked(item)
                // Update current idx
                currentClickedIdx = position
                if(prevClickedIdx != -1) notifyItemChanged(prevClickedIdx)
                notifyItemChanged(currentClickedIdx)
                // Prev is the new clicked idx
                prevClickedIdx = position
            }
        }

    }

    override fun getItemCount(): Int {
        return listFilms.size
    }

    fun addFilms(foundFilms : List<OmdbFilm?>) {
        listFilms.clear()
        listFilms.addAll(foundFilms)
        notifyDataSetChanged()
        // Reset indexes to reset selection for the next film search
        prevClickedIdx = -1
        currentClickedIdx = -1
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var filmImage: ImageView
        var filmTitle: TextView
        var filmScore : TextView
        var checkIcon: ImageView
        var container: ConstraintLayout

        constructor(itemView: View) : super(itemView) {
            filmImage = itemView.findViewById(R.id.item_searchFilm_image)
            filmTitle = itemView.findViewById(R.id.item_searchFilm_tv_title)
            filmScore = itemView.findViewById(R.id.item_searchFilm_tv_imdbScore)
            checkIcon = itemView.findViewById(R.id.item_searchFilm_iv_checkIcon)
            container = itemView.findViewById(R.id.item_searchFilm_cl_container)
        }

    }

}