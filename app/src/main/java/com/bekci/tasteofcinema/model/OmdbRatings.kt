package com.bekci.tasteofcinema.model

import com.google.gson.annotations.SerializedName

class OmdbRatings {

    @SerializedName("Source")
    private var Source: String? = null

    @SerializedName("Value")
    private var Value: String? = null

    fun setSource(source: String?) {
        Source = source
    }

    fun setValue(value: String?) {
        Value = value
    }

    fun getSource(): String? {
        return Source
    }

    fun getValue(): String? {
        return Value
    }
}