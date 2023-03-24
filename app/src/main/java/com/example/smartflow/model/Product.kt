package com.example.smartflow.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.smartflow.utils.ArrayListConverter
import com.example.smartflow.utils.ProductColorConverter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

@Entity(tableName = "product")
@Parcelize
data class Product (
    @PrimaryKey
    @SerializedName("id")
    var id: Int,

    @ColumnInfo("brand")
    @SerializedName("brand")
    var brand: String?,

    @ColumnInfo("name")
    @SerializedName("name")
    var name: String?,

    @ColumnInfo("price")
    @SerializedName("price")
    var price: String?,

    @ColumnInfo("price_sign")
    @SerializedName("price_sign")
    var pricesSign: String?,

    @ColumnInfo("currency")
    @SerializedName("currency")
    var currency: String?,

    @ColumnInfo("image_link")
    @SerializedName("image_link")
    var imageLink: String?,

    @ColumnInfo("product_link")
    @SerializedName("product_link")
    var productLink: String?,

    @ColumnInfo("website_link")
    @SerializedName("website_link")
    var websiteLink: String?,

    @ColumnInfo("description")
    @SerializedName("description")
    var description: String?,

    @ColumnInfo("category")
    @SerializedName("category")
    var category: String?,

    @ColumnInfo("rating")
    @SerializedName("rating")
    var rating: String?,

    @ColumnInfo("product_type")
    @SerializedName("product_type")
    var productType: String?,

    @ColumnInfo("tag_list")
    @SerializedName("tag_list")
    var tagList: List<String>?,


    @ColumnInfo("created_at")
    @SerializedName("created_at")
    var createdAt: String?,

    @ColumnInfo("updated_at")
    @SerializedName("updated_at")
    var updatedAt: String?,

    @ColumnInfo("product_api_url")
    @SerializedName("product_api_url")
    var productApiUrl: String?,

    @ColumnInfo("api_featured_image")
    @SerializedName("api_featured_image")
    var apiFeaturedImage: String?,

    @ColumnInfo("product_colors")
    @SerializedName("product_colors")
    var productColors: List<ProductColor>?,
    ): Parcelable {
    override fun toString(): String {
        return "Product(id=$id, brand=$brand, name=$name, price=$price, pricesSign=$pricesSign, currency=$currency, imageLink=$imageLink, productLink=$productLink, websiteLink=$websiteLink, description=$description, category=$category, rating=$rating, productType=$productType, tagList=$tagList, createdAt=$createdAt, updatedAt=$updatedAt, productApiUrl=$productApiUrl, apiFeaturedImage=$apiFeaturedImage, productColors=$productColors)"
    }
}