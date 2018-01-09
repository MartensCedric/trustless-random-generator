package com.cedricmartens;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class TrustlessGenerator<T>
{
    private String currency;
    private T[] data;

    public TrustlessGenerator(String currency, T[] data)
    {
        this.currency = currency;
        this.data = data;
    }

    public TrustlessGenerator(T[] data)
    {
        this("btc", data);
    }

    public void generate() throws IOException {
        JsonObject obj = getJsonFromURL("https://api.blockcypher.com/v1/" + currency + "/main");

        String hash = obj.getAsJsonPrimitive("hash").getAsString();
        String timestamp = obj.getAsJsonPrimitive("time").getAsString();

        System.out.println("From the " + currency + " blockchain...");
        System.out.println("Hash used : " + hash);
        System.out.println("Hash time : " + timestamp);
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        System.out.println("Time now : " + nowAsISO);
        int seed = hash.hashCode();
        System.out.println("Seed used " + Integer.toString(seed) + ", which is the hash's hashcode");
        Random r = new Random(seed);
        String result = data[r.nextInt(data.length)].toString();
        System.out.println("Result : " + result);
    }

    public static JsonObject getJsonFromURL(String sURL) throws IOException
    {
        HttpURLConnection connection = null;

        try
        {
            connection = (HttpURLConnection) new URL(sURL).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            return new JsonParser().parse(new InputStreamReader((InputStream) connection.getContent())).getAsJsonObject();
        }
        finally
        {
            connection.disconnect();
        }
    }
}
