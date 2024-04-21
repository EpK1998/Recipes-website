package org.example.storeclient.model;
import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Client {

    public Client() {
    }

    static Gson gson = new Gson();

    public static void sendToServer(Request request) {
        PrintWriter writer = null;
        try {
            // Connect to the server running on localhost at port 123123
            Socket myServer = new Socket("localhost", 123);

            // Create a PrintWriter to write data to the socket's output stream
            writer = new PrintWriter(myServer.getOutputStream(), true);

            // Convert the Request object to a JSON string using Gson's toJson method
            String x = gson.toJson(request);

            // Write the JSON string to the socket's output stream and flush it
            writer.println(x);
            System.out.println("Request sent to server: " + x);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Recipe> sendToServerShow(Request request) {
        PrintWriter writer = null;

        try {
            // Connect to the server running on localhost at port 123123
            Socket myServer = new Socket("localhost", 123);
            // Create a PrintWriter to write data to the socket's output stream
            writer = new PrintWriter(myServer.getOutputStream(), true);

            // Convert the Request object to a JSON string using Gson's toJson method
            String x = gson.toJson(request);

            // Write the JSON string to the socket's output stream and flush it
            writer.println(x);
            System.out.println("Request sent to server: " + x);
            writer.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(myServer.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                responseBuilder.append(line);
            }
            String jsonResponse = responseBuilder.toString();
            System.out.println("Response from server: " + jsonResponse);

            // Parse the response into a map of Recipe objects
            Type recipeMapType = new TypeToken<Map<Long, Recipe>>() {}.getType();
            Map<Long, Recipe> recipeMap = gson.fromJson(jsonResponse, recipeMapType);

            // Convert the map of Recipe objects into a list
            List<Recipe> recipes = new ArrayList<>(recipeMap.values());

            return recipes;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Recipe> sendToServerSearch(Request request) {
        PrintWriter writer = null;

        try {
            // Connect to the server running on localhost at port 123123
            Socket myServer = new Socket("localhost", 123);
            // Create a PrintWriter to write data to the socket's output stream
            writer = new PrintWriter(myServer.getOutputStream(), true);

            // Convert the Request object to a JSON string using Gson's toJson method
            String x = gson.toJson(request);

            // Write the JSON string to the socket's output stream and flush it
            writer.println(x);
            System.out.println("Request sent to server: " + x);
            writer.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(myServer.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                responseBuilder.append(line);
            }
            String jsonResponse = responseBuilder.toString();
            System.out.println("Response from server: " + jsonResponse);

            // Parse the response into a list of Recipe objects
            Type recipeListType = new TypeToken<List<Recipe>>() {}.getType();
            List<Recipe> recipes = gson.fromJson(jsonResponse, recipeListType);

            return recipes;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean sendToServerLogin(Request request) {
        PrintWriter writer = null;

        try {
            // Connect to the server running on localhost at port 123123
            Socket myServer = new Socket("localhost", 123);
            // Create a PrintWriter to write data to the socket's output stream
            writer = new PrintWriter(myServer.getOutputStream(), true);

            // Convert the Request object to a JSON string using Gson's toJson method
            String x = gson.toJson(request);

            // Write the JSON string to the socket's output stream and flush it
            writer.println(x);
            System.out.println("Request sent to server: " + x);
            writer.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(myServer.getInputStream()));
            boolean isValidLogin = Boolean.parseBoolean(in.readLine());
            System.out.println("Response from server: " + isValidLogin);

            // Return the boolean response to the caller
            return isValidLogin;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}