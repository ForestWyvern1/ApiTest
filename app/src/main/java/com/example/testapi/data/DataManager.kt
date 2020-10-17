package com.example.testapi.data

import android.content.Context
import android.content.SharedPreferences
import android.view.Display

class DataManager {
    private var shared : SharedPreferences
    private var baseContext : Context

    val api = Api.createApi()

    constructor(baseContext: Context){
        this.baseContext = baseContext
        shared = baseContext.getSharedPreferences("WS", Context.MODE_PRIVATE)
    }
}