package com.cedricmartens;


import java.io.*;

public class App 
{
    public static void main( String[] args ) {
        if(args.length != 0)
        {
            String filename = args[0];
            String currency = "btc";
            BufferedReader br = null;
            FileReader fr = null;
            String[] data;

            try {
                fr = new FileReader(filename);
                br = new BufferedReader(fr);
                data = br.readLine().split(",");

                if(br.readLine() == null)
                {
                    try{
                        new TrustlessGenerator<String>(currency,data).generate();
                    }catch (FileNotFoundException e)
                    {
                        System.err.println("Failed to read information from the " + currency + " blockchain at https://api.blockcypher.com/v1/" + currency + "/main");
                    }
                }else{
                    System.err.println("File not supported.\nMake sure the file separates values with commas and does not include newline characters.");
                }

            } catch (FileNotFoundException e) {
                System.err.println("File '" + filename + "' not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.err.println("Please specify a file");
        }
    }
}
