package com.example.practice1bottomnav.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


data class Task(
    val title: String? = null,
    val desc: String? = null
):Serializable
