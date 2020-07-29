package io.kubesure.multistream.util;

import io.kubesure.multistream.datatypes.Purchase;

public class JsonEventSerializerTest {

    public static void main(String args[]) throws Exception{
        JsonEventSerializer<Purchase> js = new JsonEventSerializer<Purchase>();
        Purchase p = new Purchase();
        p.setBuySell("b");
        p.setChannel("online");
        p.setRate(12.2f);
        System.out.println(js.toString(p,Purchase.class));
    }
}