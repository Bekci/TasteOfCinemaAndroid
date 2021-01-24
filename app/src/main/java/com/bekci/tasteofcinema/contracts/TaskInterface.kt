package com.bekci.tasteofcinema.contracts

import java.lang.Error

interface TaskInterface {
    fun onTaskSuccess(document: String)
    fun onTaskFailed(error: String)
}