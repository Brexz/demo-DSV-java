package com.dsv;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        //Variable to read from console
        Scanner scanner = new Scanner(System.in);

        //Storing the id from user input
        System.out.print("Insert id to hit endpoint : ");
        long id = scanner.nextLong();

        //We try to hit the endpoint given the URL and id
	    try {
	        //setup of object to connect to endpoint
            URL url = new URL("https://mytms.us.dsv.com/utiservices/tracking/simple/v2/json/paynumber/"+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            //if there is an error show the message and code error
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed, HTTP error code : " + conn.getResponseCode());
            }

            //creation of objects to show data from request
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            JSONObject json;
            String output;
            while ((output = br.readLine()) != null) {
                json = new JSONObject(output);
                System.out.println(json.toString(5));
            }

            //Close connection
            conn.disconnect();
        } catch (Exception e) {
	        //Print the exception if occurred
            System.out.println("Exception " + e);
        }
    }
}
