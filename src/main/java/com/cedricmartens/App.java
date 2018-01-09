package com.cedricmartens;


import java.io.*;

public class App 
{
    public static void main( String[] args ) {
        if(args.length != 0)
        {
            String filename = args[0];
            BufferedReader br = null;
            FileReader fr = null;
            String[] data;

            try {
                fr = new FileReader(filename);
                br = new BufferedReader(fr);
                data = br.readLine().split(",");
                new TrustlessGenerator<String>(data).generate();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }else{
            System.err.println("Please specify a file");
        }
    }
}
