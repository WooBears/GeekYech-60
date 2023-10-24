package com.example.practice1bottomnav.ui.data.local

import android.content.Context
import android.net.Uri

class Pref (context: Context){

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    fun isShow():Boolean
    {
        return pref.getBoolean(SHOWED_KEY,false)
    }

    fun onShowed()
    {
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

    fun saveName(name: String)
    {
        pref.edit().putString(NAME_TEXT_KEY,name).apply()
    }

    fun getName():String? {
        return pref.getString(NAME_TEXT_KEY,"")
    }

    fun saveImage(photo: String)
    {
        pref.edit().putString(IMAGE_SAVE_KEY,photo).apply()
    }

    fun getImage():String?
    {
        return pref.getString(IMAGE_SAVE_KEY,null)
    }
    companion object
    {
        const val PREF_NAME = "pref.folder.name"
        const val SHOWED_KEY = "showed.key"
        const val NAME_TEXT_KEY = "showed.text.key"
        const val IMAGE_SAVE_KEY = "image.key"
    }
}