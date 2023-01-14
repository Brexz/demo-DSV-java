package com.dsv;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert id to hit endpoint : ");
        long id = scanner.nextLong();

	    try {
            URL url = new URL("https://mytms.us.dsv.com/utiservices/tracking/simple/v2/json/paynumber/"+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed, HTTP error code : " + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            JSONObject json;
            String output;
            while ((output = br.readLine()) != null) {
                json = new JSONObject(output);
                System.out.println(json.toString(5));

            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
    }
}
