package com.dbtechprojects.cloudzy.database

import androidx.room.TypeConverter
import com.dbtechprojects.cloudzy.model.AffectedProduct
import com.dbtechprojects.cloudzy.model.Guid
import com.dbtechprojects.cloudzy.model.MostRecentUpdate
import com.dbtechprojects.cloudzy.model.Update
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken




class Converters {

    private val gson = Gson()

    @TypeConverter
    fun Guid.convertGuidToString(): String {
        val stringToReturn = StringBuilder()
        stringToReturn.append("$text;$isPermaLink")
        return stringToReturn.toString()
    }

    @TypeConverter
    fun String.convertStringToGuid(): Guid {
        val text = StringBuilder()
        val isPermaLinkString = StringBuilder()
        for (each in this.withIndex()) {
            val indexOfDelimiter = this.indexOf(';')
            when {
                each.index < indexOfDelimiter -> text.append(each.value)
                each.index == indexOfDelimiter -> continue
                each.index > indexOfDelimiter -> isPermaLinkString.append(each.value)
            }
        }
        return Guid(text.toString(), isPermaLinkString.toString().toBoolean())
    }

    @TypeConverter
    fun List<AffectedProduct>.convertAffectedProductListToString(): String? {
        val type = object : TypeToken<List<AffectedProduct>>() {}.type
        return gson.toJson(this, type)
    }

    @TypeConverter
    fun String.convertStringToAffectedProductList(): List<AffectedProduct> {
        val type = object : TypeToken<List<AffectedProduct>>() {}.type
        return gson.fromJson(this, type)
    }

    @TypeConverter
    fun String.convertStringToUpdateList(): List<Update> {
        val type = object : TypeToken<List<Update>>() {}.type
        return gson.fromJson(this, type)
    }

    @TypeConverter
    fun List<Update>.convertUpdateListToString(): String {
        val type = object : TypeToken<List<Update>>() {}.type
        return gson.toJson(this, type)
    }


    @TypeConverter
    fun MostRecentUpdate.convertMostRecentUpdateToString(): String {
        val type = object : TypeToken<MostRecentUpdate>() {}.type
        return gson.toJson(this, type)
    }

    @TypeConverter
    fun String.convertStringToMostRecentUpdate(): MostRecentUpdate {
        val type = object : TypeToken<MostRecentUpdate>() {}.type
        return gson.fromJson(this, type)
    }
}
