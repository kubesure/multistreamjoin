package io.kubesure.multistream.util;

import java.lang.reflect.Type;

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

import io.kubesure.multistream.datatypes.Payment;
import io.kubesure.multistream.datatypes.Purchase;

public class Convertor {

       
    // TODO: Create a generic based method to serialize
    public static Payment convertToPayment(String payment) throws Exception{
        Payment p = newGson().fromJson(payment, Payment.class);
        return p;
    }

    // TODO: Create a generic based method to serialize
    public static String convertPurchaseToJson(Purchase purchase) throws Exception{
        String p = newGson().toJson(purchase, Purchase.class);
        return p;
    }

    // TODO: Create a generic based method to serialize
    public static String convertPaymentToJson(Payment payment) throws Exception{
        String p = newGson().toJson(payment,Payment.class);
        return p;
    }

    // TODO: Create a generic based method to serialize
    public static Purchase convertToPurchase(String purchase) throws Exception{
        Purchase p = newGson().fromJson(purchase, Purchase.class);
        return p;
    }

    private static Gson newGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
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