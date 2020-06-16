package io.kubesure.multistream.util;

import java.lang.reflect.Type;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;

import io.kubesure.multistream.datatypes.Deal;
import io.kubesure.multistream.datatypes.Payment;
import io.kubesure.multistream.datatypes.Purchase;

public class JsonEventSerializer<T> {


    public <T> T toType(String json, Class<T> tt) throws Exception{
        Type type = new TypeToken<Class<T>>(){}.getType();
        Object o = tt.getConstructor(tt).newInstance(tt.getName());
        return new GsonBuilder().registerTypeAdapter(type, o).create().fromJson(json, type);
        //newGson().fromJson(json, tt.isInstance(tt);
    }

    public <T> String toString(T object,Class<T> tt) throws Exception{
        Type type = new TypeToken<Class<T>>(){}.getType();
        Object o = tt.getConstructor(tt).newInstance(tt.getName());
        return new GsonBuilder().registerTypeAdapter(type, o).create().toJson(o, type);
    }

    private Gson newGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
        gsonBuilder.registerTypeAdapter(Payment.class, new Payment());
        gsonBuilder.registerTypeAdapter(Purchase.class, new Purchase());
        gsonBuilder.registerTypeAdapter(Deal.class, new Deal());
        Gson gson = gsonBuilder.create();
        return gson;
    }

    private static class DateTimeTypeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
        // No need for an InstanceCreator since DateTime provides a no-args constructor
        @Override
        public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
          return new JsonPrimitive(src.toString(TimeUtil.isoFormatter));
        }
        @Override
        public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
            DateTime eventTime = TimeUtil.isoFormatter.parseDateTime(json.getAsString());      
            return eventTime;
        }
    }
    
}