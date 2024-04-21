package server;

import com.google.gson.Gson;
import dao.RecipeDaoImpl;
import dao.UserDaoImpl;
import dm.Recipe;
import dm.User;
import services.RecipeService;
import java.util.ArrayList;
import java.util.HashMap;

public class ControllerC {
    private Gson gson = new Gson();
    private UserDaoImpl userDao;
    private RecipeDaoImpl recipeDao;
    private  RecipeService recipeService;

    public ControllerC(RecipeService recipeService){

        this.recipeService = recipeService;
        this.userDao = new UserDaoImpl();
        this.recipeDao = new RecipeDaoImpl();
    }


    // Function to save a new recipe
    public void saveRecipe(Recipe recipe) {
        recipeService.addNewRecipe(recipe);
    }

    // Function to delete a recipe
    public void deleteRecipe(long recipeId) {
        if (recipeId != 0) {
            recipeService.deleteRecipe(recipeId);
        } else {
            System.out.println("Recipe with ID " + recipeId + " not found.");
        }
    }

    // Function to get a recipe by ID
    public Recipe getRecipe(String recipeId) {
        return recipeDao.find(Long.parseLong(recipeId));
    }


    // Function to add a rating to a specific recipe
    public void addRateToRecipe(Long recipeId, int Rate) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        if (recipe != null) {
            recipeDao.addRating(recipe,Rate);
        } else {
            System.out.println("Recipe with ID " + recipeId + " not found.");
        }
    }
    public String findByTitle(String pattern)
    {
       ArrayList<Recipe> recipes = recipeDao.searchRecipesByTitle(pattern);
       return gson.toJson(recipes);
    }

    // Function to save a new user
    public void saveUser(User user) {
        userDao.save(user);
    }

    // Function to delete a user
    public String findByNameAndId(String name, Long id) {
        HashMap<Long, User> users = userDao.readUsersFromFile();
        if (users != null) {
            for (User user : users.values()) {
                if (user.getName().equals(name) && user.getId().equals(id)) {
                    return gson.toJson(true);
                }
            }
        }
        return gson.toJson(false);
    }

    // Function to get a user by ID
    public User getUser(String userId) {
        return userDao.find(Long.parseLong(userId));
    }


    public String getAllRecipesAsJson() {
        return recipeService.getAllRecipesAsJson();
    }


    // Function to get all users

}