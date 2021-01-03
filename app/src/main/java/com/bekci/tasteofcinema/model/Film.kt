package com.bekci.tasteofcinema.model

import android.os.Parcel
import android.os.Parcelable

class Film() : Parcelable {
    var title : String? = ""
    var detail: String? = ""
    var year: Int = 0
    var imdbID: String? = ""
    var imgURL: String? = ""

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        detail = parcel.readString()
        year = parcel.readInt()
        imdbID = parcel.readString()
        imgURL = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeString(detail)
        dest?.writeInt(year)
        dest?.writeString(imdbID)
        dest?.writeString(imgURL)
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}