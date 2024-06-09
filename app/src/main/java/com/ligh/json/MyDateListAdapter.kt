package com.ligh.json

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MyDateListAdapter : JsonDeserializer<List<MyDate>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<MyDate> {
        val list = mutableListOf<MyDate>()
        json?.asJsonArray?.forEach { element ->
            val jsonObj = element.asJsonObject
            val year = jsonObj["year"].asInt
            val month = jsonObj["month"].asInt
            val day = jsonObj["day"].asInt
            list.add(MyDate(year, month, day))
        }
        return list
    }
}
