package services;
import com.google.gson.Gson;
import dao.RecipeDaoImpl;
import dao.UserDaoImpl;
import dm.Recipe;
import java.util.ArrayList;
import java.util.HashMap;



public class RecipeService {
    private Gson gson = new Gson();
    private RecipeDaoImpl recipeDao;
    private UserDaoImpl userDao;
    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }



    public RecipeService(RecipeDaoImpl recipeDao, UserDaoImpl userDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
    }

    public void addNewRecipe(Recipe recipe) {
        recipeDao.save(recipe);
    }

    public Recipe findRecipeById(Long id) {
        return recipeDao.find(id);
    }

    public Recipe updateRecipe(Recipe recipe) {
        return recipeDao.updateRecipe(recipe);
    }

    public void deleteRecipe(Long recipeId) {
        recipeDao.delete(findRecipeById(recipeId));
    }


    public String getAllRecipesAsJson() {
        HashMap<Long, Recipe> recipes = recipeDao.readRecipesFromFile();
        return gson.toJson(recipes);
    }

    public void addRatingservice(Recipe recipe, int rating) {
        if (rating >= 0 && rating <= 5) {
            recipe.getRatings().add((double) rating);
            // Calculate mean and update in DB
            updateMeanRatingservice(recipe);
        } else {
            System.out.println("Invalid rating. Rating should be between 0 and 5.");
        }
    }
    private void updateMeanRatingservice(Recipe recipe) {
        ArrayList<Double> ratings = recipe.getRatings();
        double sum = 0;
        for (Double rating : ratings) {
            sum += rating;
        }
        double meanRating = sum / ratings.size();
        // Now you can save the meanRating in the database using the DAO class
        // For simplicity, let's assume you have a DAO class called RecipeDaoImpl
        RecipeDaoImpl recipeDao = new RecipeDaoImpl();
        recipe.setMeanRating(meanRating);
        recipeDao.save(recipe);
    }

}