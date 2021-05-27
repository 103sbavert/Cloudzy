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
}
