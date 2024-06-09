package com.ligh.json;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MyDateAdapter implements JsonDeserializer<MyDate> {
    @Override
    public MyDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        int year = jsonObject.get("year").getAsInt();
        int month = jsonObject.get("month").getAsInt();
        int day = jsonObject.get("day").getAsInt();

        return new MyDate(year, month, day);
    }
}
