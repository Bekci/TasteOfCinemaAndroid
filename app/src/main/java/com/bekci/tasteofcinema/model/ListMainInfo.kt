package com.bekci.tasteofcinema.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class ListMainInfo() : Parcelable {
    var title: String? = ""
    var detail: String? = ""
    var imgURL: String? = ""
    var date: String? = ""
    var url: String? = ""

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        detail = parcel.readString()
        imgURL = parcel.readString()
        date = parcel.readString()
        url = parcel.readString()
    }

    constructor(title: String, detail: String, imgURL : String, date: String, lURL: String) : this() {
        this.title = title
        this.date = date
        this.imgURL = imgURL
        this.detail = detail
        this.url = lURL

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(detail)
        parcel.writeString(imgURL)
        parcel.writeString(date)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListMainInfo> {
        override fun createFromParcel(parcel: Parcel): ListMainInfo {
            return ListMainInfo(parcel)
        }

        override fun newArray(size: Int): Array<ListMainInfo?> {
            return arrayOfNulls(size)
        }
    }
}