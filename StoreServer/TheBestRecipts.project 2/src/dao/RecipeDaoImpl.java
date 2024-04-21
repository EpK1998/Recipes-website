
package dao;


import PartA.BoyerMoore;
import PartA.KMP;
import dm.Recipe;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class RecipeDaoImpl implements IDao<Long, Recipe> {

    public static final String FILE_PATH = "C:\\Users\\97254\\Desktop\\TheBestRecipts.project 3\\TheBestRecipts.project 3\\TheBestRecipts.project 2\\Resorce\\recipefile.txt";

    public RecipeDaoImpl() {
        try {
            ObjectOutputStream objectOutputStream;
            boolean emptyData = emptyData();
            if (emptyData) {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
                objectOutputStream.writeObject(new HashMap<Long, Recipe>());
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Recipe entity) {
        HashMap<Long, Recipe> recipes = readRecipesFromFile();
        if (recipes != null) {
            recipes.remove(entity.getId());
            saveRecipesToFile(recipes);
        }
    }

    public Recipe updateRecipe(Recipe recipe) {
        Recipe existingRecipe = find(recipe.getId());
        if (existingRecipe != null) {
            existingRecipe.setChef(recipe.getChef());
            existingRecipe.setTitle(recipe.getTitle());
            existingRecipe.setDescription(recipe.getDescription());
            // Update the existing recipe in the database
            save(existingRecipe);
            return existingRecipe;
        }
        return null; // Recipe with given ID not found
    }

    @Override
    public Recipe find(Long id) throws IllegalArgumentException {
        HashMap<Long, Recipe> recipes = readRecipesFromFile();
        if (recipes != null) {
            return recipes.get(id);
        }
        return null;
    }
    public ArrayList<Recipe> searchRecipesByTitle(String pattern) {
        ArrayList<Recipe> foundRecipes = new ArrayList<>();
        HashMap<Long, Recipe> recipes = readRecipesFromFile();
        if (recipes != null) {
            KMP kmp = new KMP();
            for (Recipe recipe : recipes.values()) {
                String title = recipe.getTitle().toLowerCase();
                if (kmp.search(pattern.toLowerCase(), title).size() > 0) {
                    foundRecipes.add(recipe);
                }
            }
        }
        return foundRecipes;
    }

    public ArrayList<Recipe> searchRecipesByTitleBM(String pattern) {
        ArrayList<Recipe> foundRecipes = new ArrayList<>();
        HashMap<Long, Recipe> recipes = readRecipesFromFile();
        if (recipes != null) {
            BoyerMoore bm = new BoyerMoore();
            for (Recipe recipe : recipes.values()) {
                String title = recipe.getTitle().toLowerCase();
                if (bm.search(pattern.toLowerCase(), title).size() > 0) {
                    foundRecipes.add(recipe);
                }
            }
        }
        return foundRecipes;
    }

    @Override
    public boolean save(Recipe recipe) throws IllegalArgumentException {
        HashMap<Long, Recipe> recipes = readRecipesFromFile();
        if (recipes != null) {
            recipes.put(recipe.getId(), recipe);
            saveRecipesToFile(recipes);
            return true;
        }
        return false;
    }

    private boolean emptyData() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        return !file.exists() || file.length() == 0;
    }

    public HashMap<Long, Recipe> readRecipesFromFile() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            return (HashMap<Long, Recipe>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveRecipesToFile(HashMap<Long, Recipe> recipes) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            objectOutputStream.writeObject(recipes);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addRating(Recipe recipe, int rating) {
        if (rating >= 0 && rating <= 5) {
            recipe.getRatings().add((double) rating);
            // Calculate mean and update in DB
            updateMeanRating(recipe);
        } else {
            System.out.println("Invalid rating. Rating should be between 0 and 5.");
        }
    }

    // Method to update the mean rating of a recipe
    private void updateMeanRating(Recipe recipe) {
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