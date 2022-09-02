package com.sike.app

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class RumahSakit (
    val imgRs: Int,
    val nameRs: String,
    val locRs: String,
    val descRs: String,
): Parcelable
