package com.example.uvenco.entity

import android.content.Context
import com.example.uvenco.ui.log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorApp @Inject constructor(@ApplicationContext val context: Context) {

    fun errorApi (errorMessage:String){
        log(true, "Error: $errorMessage")
    }
}

