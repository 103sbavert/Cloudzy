package com.dbtechprojects.cloudstatustest.database

import androidx.room.TypeConverter
import com.dbtechprojects.cloudstatustest.model.Guid

class Converters {

    @TypeConverter
    fun Guid.convertToString(): String {
        val stringToReturn = StringBuilder()
        stringToReturn.append("$text;$isPermaLink")
        return stringToReturn.toString()
    }

    @TypeConverter
    fun String.convertToGuid(): Guid {
        var text = ""
        var isPermaLinkString = ""
        for (each in this.withIndex()) {
            val indexOfDelimiter = this.indexOf(';')
            when {
                each.index < indexOfDelimiter -> text += each.value
                each.index == indexOfDelimiter -> continue
                each.index > indexOfDelimiter -> isPermaLinkString += each.value
            }
        }
        return Guid(text, isPermaLinkString.toBoolean())
    }
}
