package com.dbtechprojects.cloudstatustest.database

import androidx.room.TypeConverter
import com.dbtechprojects.cloudstatustest.model.AffectedProduct
import com.dbtechprojects.cloudstatustest.model.Guid
import com.dbtechprojects.cloudstatustest.model.MostRecentUpdate
import com.dbtechprojects.cloudstatustest.model.Update
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun Guid.convertToString(): String {
        val stringToReturn = StringBuilder()
        stringToReturn.append("$text;$isPermaLink")
        return stringToReturn.toString()
    }

    @TypeConverter
    fun String.convertToGuid(): Guid {
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
    fun List<AffectedProduct>.convertAPToString(): String? {
        val type = object : TypeToken<List<AffectedProduct>>() {}.type
        return Gson().toJson(this, type)
    }

    @TypeConverter
    fun String.convertToAffectedProductList(): List<AffectedProduct> {
        val type = object : TypeToken<List<AffectedProduct>>() {}.type
        return Gson().fromJson<List<AffectedProduct>>(this, type)
    }

    @TypeConverter
    fun MostRecentUpdate.convertToString(): String? {
        val type = object : TypeToken<MostRecentUpdate>() {}.type
        return Gson().toJson(this, type)
    }

    @TypeConverter
    fun String.convertToMostRecentUpdate(): MostRecentUpdate {
        val type = object : TypeToken<MostRecentUpdate>() {}.type
        return Gson().fromJson<MostRecentUpdate>(this, type)
    }


    @TypeConverter
    fun List<Update>.convertUToString(): String? {
        val type = object : TypeToken<List<Update>>() {}.type
        return Gson().toJson(this, type)
    }

    @TypeConverter
    fun String.convertToUpdateList(): List<Update> {
        val type = object : TypeToken<List<Update>>() {}.type
        return Gson().fromJson<List<Update>>(this, type)
    }
}
