package com.dicoding.tugasbasicandroid.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artikel(val nama: String, val deskripsi: String, val gambar: Int): Parcelable
