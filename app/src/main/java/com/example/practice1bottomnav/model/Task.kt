package com.example.practice1bottomnav.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var title: String? = null,
    var desc: String? = null
): Serializable

