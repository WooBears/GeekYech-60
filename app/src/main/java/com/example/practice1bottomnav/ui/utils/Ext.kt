package com.example.practice1bottomnav.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?)
{
    Glide.with(this).load(url).into(this)
}