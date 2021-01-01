package com.bekci.tasteofcinema

import android.os.AsyncTask
import com.bekci.tasteofcinema.`interface`.TaskInterface
import com.bekci.tasteofcinema.model.ListMainInfo
import org.jsoup.Jsoup
import java.lang.Error

class ParserTask : AsyncTask<String?, Void, String>() {

    var taskInterface : TaskInterface? = null

    override fun doInBackground(vararg params: String?): String? {
        val url = params[0]
        try {
            val document = Jsoup.connect(url).get()
            return document.toString()
        }catch (err: Error){
            taskInterface?.onTaskFailed(err)
        }
        return null
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        taskInterface?.onTaskSuccess(result.toString())
    }
}