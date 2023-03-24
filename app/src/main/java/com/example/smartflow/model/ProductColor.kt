package com.example.smartflow.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

@Parcelize
data class ProductColor(
    @SerializedName("hex_value")
    var hexValue: String?,

    @SerializedName("colour_name")
    var colourName: String?,
): Parcelable
