package com.example.neospacecompose.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.neospacecompose.repository.image_convertors.Converters
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.Serializable
import java.util.ArrayList

@Entity(tableName = "products")
data class Products(

    @PrimaryKey @ColumnInfo @SerializedName("id") var id: Int? = null,
    @ColumnInfo @SerializedName("title") var title: String? = null,
    @ColumnInfo @SerializedName("description") var description: String? = null,
    @ColumnInfo @SerializedName("price") var price: Int? = null,
    @ColumnInfo @SerializedName("discountPercentage") var discountPercentage: Double? = null,
    @ColumnInfo @SerializedName("rating") var rating: Double? = null,
    @ColumnInfo @SerializedName("stock") var stock: Int? = null,
    @ColumnInfo @SerializedName("brand") var brand: String? = null,
    @ColumnInfo @SerializedName("category") var category: String? = null,
    @ColumnInfo @SerializedName("thumbnail") var thumbnail: String? = null,
    //@ColumnInfo @SerializedName("images") var images: List<String> = arrayListOf()
    /*@ColumnInfo(name = "images")
    var images :ArrayList<String> = arrayListOf()*/
)