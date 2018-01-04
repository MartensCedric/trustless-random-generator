package com.cedricmartens;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        BufferedReader br = null;
        FileReader fr = null;
        String[] data;
        String currencyTag = "btc";
        try {

            fr = new FileReader("data.csv");
            br = new BufferedReader(fr);

            data = br.readLine().split(",");
            JsonObject obj = getJsonFromURL("https://api.blockcypher.com/v1/" + currencyTag + "/main");

            String hash = obj.getAsJsonPrimitive("hash").getAsString();
            String timestamp = obj.getAsJsonPrimitive("time").getAsString();

            System.out.println("From the " + currencyTag + " blockchain...");
            System.out.println("Hash used : " + hash);
            System.out.println("Hash time : " + timestamp);

            String shortHash = hash.substring(hash.length()-7, hash.length());
            int seed = shortHash.hashCode();
            System.out.println("Seed used " + Integer.toString(seed) + ", which is " + shortHash + "'s hashcode");
            Random r = new Random(seed);
            String result = data[r.nextInt(data.length)];
            System.out.println("Result : " + result);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
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
