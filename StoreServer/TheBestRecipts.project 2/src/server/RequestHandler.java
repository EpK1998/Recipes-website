package server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dm.Recipe;
import dm.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.io.*;



public class RequestHandler{
    private ControllerC controllerCC;
    private Socket someClient;
    Gson g = new GsonBuilder().create();

    public RequestHandler(Socket someClient, ControllerC ControllerCC){
        this.someClient = someClient;
        this.controllerCC = ControllerCC;

    }
    static long idrecipe = 5;
    public void process() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.someClient.getInputStream()));
        String line = reader.readLine();
        server.Request request = g.fromJson(line, Request.class);
        PrintWriter writer = new PrintWriter(this.someClient.getOutputStream(), true);
        String action = request.getAction();
        switch (action){
            case "addRecipe":
                Map<String, Object> body = request.getBody();
                Long id = idrecipe;
                idrecipe++;
                String chef = (String) body.get("chef");
                String title = (String) body.get("title");
                String description = (String) body.get("description");
                Recipe recipef = new Recipe(id, chef, title, description);
                controllerCC.saveRecipe(recipef);
                break;
            case "deleteRecipe":
                Map<String, Object> body1 = request.getBody();
                String bodyStr2 = g.toJson(body1);
                Recipe recipe3 = g.fromJson(bodyStr2, Recipe.class);
                controllerCC.deleteRecipe(recipe3.getId());
                break;
            case "getAllRecipes":
                String responseJson = controllerCC.getAllRecipesAsJson();
                // Send the JSON response back to the client
                writer.println(responseJson);
                writer.flush();
                System.out.println("Sent all recipes to client: " + responseJson);
                break;
            case "AddRating":
                Map<String, Object> ratingBody = request.getBody();
                Long recipeId = ((Double) ratingBody.get("id")).longValue(); // Assuming recipeId is a numeric value
                int rating = ((Double) ratingBody.get("rating")).intValue(); // Assuming rating is a numeric value
                controllerCC.addRateToRecipe(recipeId, rating);
                break;
            case "SearchByTitle":
                Map<String, Object> searchBody = request.getBody();
                if (searchBody.containsKey("title") && searchBody.get("title") != null) {
                    String searchTitle = searchBody.get("title").toString();
                    String searchResponseJson = controllerCC.findByTitle(searchTitle);
                    writer.println(searchResponseJson);
                    writer.flush();
                    System.out.println("Sent all recipes to client: " + searchResponseJson);
                } else {
                    System.out.println("Invalid search request: title is missing or null.");
                }
                break;
            case "LogIn":
                Map<String, Object> loginBody = request.getBody();
                if (loginBody.containsKey("id") && loginBody.get("name") != null) {
                    Long idsearch =  Long.parseLong(loginBody.get("id").toString());
                    String namesearch = loginBody.get("name").toString();
                    String searchResponseJson = controllerCC.findByNameAndId(namesearch,idsearch);
                    writer.println(searchResponseJson);
                    writer.flush();
                    System.out.println("Sent IsFound to client: " + searchResponseJson);
                } else {
                    System.out.println("Invalid Log in request: fields is missing or null.");
                }
                break;
            case "AddUser":
                Map<String, Object> bodyuser = request.getBody();
                String name = (String) bodyuser.get("name");
                Long iduser = ((Double) bodyuser.get("id")).longValue();
                User usertosave = new User(iduser,name);
                controllerCC.saveUser(usertosave);
                break;







        }
        reader.close();
//        PrintWriter writer = new PrintWriter(this.someClient.getOutputStream(), true);
        System.out.println(request);

    }

    private void close(ObjectOutputStream output, ObjectInputStream input) throws IOException {
        output.close();
        input.close();
        someClient.close();
    }
}