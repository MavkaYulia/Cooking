package com.example.cookingnew.ui.my_recipes

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "Recipes")
@Parcelize()
data class recipe(@PrimaryKey(autoGenerate = true) val id: Long?,
                  @ColumnInfo(name = "name") var nameR: String,
                  @ColumnInfo(name = "type") var type: String,
                  @ColumnInfo(name = "time_cooking") var timeCooking: String,
                  @ColumnInfo(name = "url") var url: String,
                  @ColumnInfo(name = "describe") var describe: String,
                  @ColumnInfo(name = "ingredients") var ingredients: String
                   ): Parcelable
{

    fun getName(): String {
        return nameR
     }
}
//class recipe(var nameR: String , var wocb: String,var url: String,var site: String){

  //

//}