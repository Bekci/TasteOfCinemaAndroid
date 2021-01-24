package com.bekci.tasteofcinema.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.bekci.tasteofcinema.R

object AlertDialogUtil {

    fun openAlertDialog(context: Context, message: String, posMessage: String?,
                        negMessage: String?, posFunc: () -> (Unit), negFunc: () -> (Unit), cancellable: Boolean?){
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        posMessage?.let {
            builder.setPositiveButton(it
            ) { dialog, which -> posFunc()}
        }
        negMessage?.let {
            builder.setNegativeButton(it
            ) { dialog, which -> negFunc()}
        }
        cancellable?.let {
            builder.setCancelable(it)
        }
        builder.create().show()
    }

    fun openNoInternetDialog(context: Context, activity: Activity){
        openAlertDialog(context, context.getString(R.string.err_no_internet), posMessage = "EXIT",
            cancellable = false, posFunc = {activity.finishAndRemoveTask()}, negFunc = {}, negMessage = null)
    }
}