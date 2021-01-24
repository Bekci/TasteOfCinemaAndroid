package com.bekci.tasteofcinema

import android.os.AsyncTask
import com.bekci.tasteofcinema.contracts.TaskInterface
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import java.lang.Error

class ParserTask : AsyncTask<String?, Void, String>() {

    var taskInterface : TaskInterface? = null

    override fun doInBackground(vararg params: String?): String? {
        val url = params[0]
        try {
            val document = Jsoup.connect(url).get()
            return document.toString()
        }catch (err: Exception){
            taskInterface?.onTaskFailed(err.message.toString())
        }
        return null
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        result?.let {
            taskInterface?.onTaskSuccess(it.toString())
        }
    }
}