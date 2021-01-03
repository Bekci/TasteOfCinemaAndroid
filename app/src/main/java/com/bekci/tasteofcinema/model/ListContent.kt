package com.bekci.tasteofcinema.model

import android.os.Parcel
import android.os.Parcelable

class ListContent() : Parcelable{
    var title : String? = ""
    var detail: String? = ""
    var imgURL: String? = ""
    var listURL: String? =""
    var films: MutableList<Film> = mutableListOf()
    var numPages = 0
    var date: String? = ""

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        detail = parcel.readString()
        imgURL = parcel.readString()
        listURL = parcel.readString()
        numPages = parcel.readInt()
        date = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(detail)
        parcel.writeString(imgURL)
        parcel.writeString(listURL)
        parcel.writeInt(numPages)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListContent> {
        override fun createFromParcel(parcel: Parcel): ListContent {
            return ListContent(parcel)
        }

        override fun newArray(size: Int): Array<ListContent?> {
            return arrayOfNulls(size)
        }
    }
}