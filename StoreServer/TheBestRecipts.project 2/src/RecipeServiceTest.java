import dm.User;
import org.junit.Before;
import org.junit.Test;

import dao.RecipeDaoImpl;
import dao.UserDaoImpl;
import dm.Recipe;
import services.RecipeService;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RecipeServiceTest {

    private RecipeService recipeService;

    @Before
    public void setUp() {
        // Set up RecipeDaoImpl and UserDaoImpl instances
        RecipeDaoImpl recipeDao = new RecipeDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        recipeService = new RecipeService(recipeDao, userDao);
    }

    @Test
    public void testAddNewRecipe() {
        Recipe recipe = new Recipe(30L, "rafi", "rafi", "Delicious rafi");
        recipeService.addNewRecipe(recipe);

        Recipe retrievedRecipe = recipeService.findRecipeById(30L);
        assertNotNull(retrievedRecipe);
        assertEquals("rafi", retrievedRecipe.getTitle());
    }
    @Test
    public void testAddRatingToRecipe() {
        Recipe recipe = new Recipe(1L, "Chef", "Title", "Description");
        recipeService.addNewRecipe(recipe);

        recipeService.addRatingservice(recipe, 4);
        recipeService.addRatingservice(recipe, 5);
        recipeService.addRatingservice(recipe, 3);

        Recipe updatedRecipe = recipeService.findRecipeById(1L);
        assertNotNull(updatedRecipe);
        assertEquals(3, updatedRecipe.getRatings().size());

        assertEquals(4.0, updatedRecipe.getMeanRating(), 0.01);
    }
    @Test
    public void testDeleteRecipe() {
        Recipe recipe = new Recipe(1L, "Chef", "Title", "Description");
        recipeService.addNewRecipe(recipe);

        recipeService.deleteRecipe(1L);

        Recipe deletedRecipe = recipeService.findRecipeById(1L);
        assertNull(deletedRecipe);
    }
    @Test
    public void testGetAllRecipesAsJson() {
        Recipe recipe1 = new Recipe(1L, "Chef", "Title1", "Description1");
        Recipe recipe2 = new Recipe(2L, "Chef", "Title2", "Description2");

        recipeService.addNewRecipe(recipe1);
        recipeService.addNewRecipe(recipe2);

        String jsonResult = recipeService.getAllRecipesAsJson();

        assertTrue(jsonResult.contains("Title1"));
        assertTrue(jsonResult.contains("Title2"));
    }
    @Test
    public void testUpdateRecipe() {
        Recipe recipe = new Recipe(40L, "John", "Chicken Curry", "Delicious chicken curry recipe");
        recipeService.addNewRecipe(recipe);

        recipe.setTitle("Updated Chicken Curry");
        recipe.setDescription("Updated description of chicken curry recipe");

        recipeService.updateRecipe(recipe);

        Recipe updatedRecipe = recipeService.findRecipeById(40L);

        assertNotNull(updatedRecipe);
        assertEquals("Updated Chicken Curry", updatedRecipe.getTitle());
        assertEquals("Updated description of chicken curry recipe", updatedRecipe.getDescription());
    }

    @Test
    public void testSearchTime() {
        RecipeDaoImpl kmpTest = new RecipeDaoImpl(); // Replace YourClass with the actual class name

        // Prepare the path to the text file containing sample recipe titles
        String filePath = "C:\\Users\\97254\\Desktop\\TheBestRecipts.project 3\\TheBestRecipts.project 3\\TheBestRecipts.project 2\\Resorce\\recipefile.txt";

        // Read the pattern from the text file
        StringBuilder patternBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                patternBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pattern = patternBuilder.toString();

        // Measure the time taken to find the pattern
        long startTime = System.currentTimeMillis();
        ArrayList<Recipe> foundRecipes = kmpTest.searchRecipesByTitle(pattern);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Print out the time taken
        System.out.println("Time taken to find patterns: " + elapsedTime + " milliseconds");

        // Add your assertions here based on your requirements
        assertNotNull(foundRecipes); // Check if the result is not null
        // Add more assertions as needed
    }
    @Test
    public void testSearchTimeForBeefKMP() {
        RecipeDaoImpl kmpTest = new RecipeDaoImpl(); // Replace RecipeDaoImpl with the actual class name

        // Prepare the path to the text file containing sample recipe titles
        String filePath = "C:\\Users\\rafi7\\Downloads\\TheBestRecipts.project 2\\TheBestRecipts.project 2\\Resorce\\recipefile.txt";

        // Pattern to search
        String pattern = "beef";

        // Measure the time taken to find the pattern "beef" using the KMP algorithm
        long startTime = System.currentTimeMillis();
        ArrayList<Recipe> foundRecipes = kmpTest.searchRecipesByTitle(pattern);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Print out the time taken
        System.out.println("Time taken to find patterns for 'beef': " + elapsedTime + " milliseconds");

        // Add your assertions here based on your requirements
        assertNotNull(foundRecipes); // Check if the result is not null
        // Add more assertions as needed
        for (Recipe recipe : foundRecipes) {
            assertTrue(recipe.getTitle().toLowerCase().contains(pattern)); // Ensure each found recipe contains the title "beef"
        }
    }
    @Test
    public void testSearchTimeForBeefBM() {
        RecipeDaoImpl kmpTest = new RecipeDaoImpl(); // Replace RecipeDaoImpl with the actual class name

        // Prepare the path to the text file containing sample recipe titles
        String filePath = "C:\\Users\\rafi7\\Downloads\\TheBestRecipts.project 2\\TheBestRecipts.project 2\\Resorce\\recipefile.txt";

        // Pattern to search
        String pattern = "beef";

        // Measure the time taken to find the pattern "beef" using the KMP algorithm
        long startTime = System.currentTimeMillis();
        ArrayList<Recipe> foundRecipes = kmpTest.searchRecipesByTitle(pattern);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Print out the time taken
        System.out.println("Time taken to find patterns for 'beef': " + elapsedTime + " milliseconds");

        // Print the indices where "beef" is found
        if (!foundRecipes.isEmpty()) {
            System.out.println("Indices where 'beef' is found:");
            for (Recipe recipe : foundRecipes) {
                String title = recipe.getTitle().toLowerCase();
                int index = title.indexOf(pattern);
                while (index != -1) {
                    System.out.println("Recipe ID: " + recipe.getId() + ", Index: " + index);
                    index = title.indexOf(pattern, index + 1);
                }
            }
        } else {
            System.out.println("No recipes containing 'beef' found.");
        }

        // Add your assertions here based on your requirements
        assertNotNull(foundRecipes); // Check if the result is not null
        // Add more assertions as needed
        for (Recipe recipe : foundRecipes) {
            assertTrue(recipe.getTitle().toLowerCase().contains(pattern)); // Ensure each found recipe contains the title "beef"
        }
    }


    @Test
    public void testSearchTimeBoyerMooreForBeef() {
        RecipeDaoImpl boyerMooreTest = new RecipeDaoImpl(); // Replace RecipeDaoImpl with the actual class name

        // Prepare the pattern to search for the title "beef"
        String pattern = "beef";

        // Measure the time taken to find the pattern "beef" using Boyer-Moore algorithm
        long startTime = System.currentTimeMillis();
        ArrayList<Recipe> foundRecipes = boyerMooreTest.searchRecipesByTitleBM(pattern); // Assuming you have a method for Boyer-Moore search
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Print out the time taken
        System.out.println("Time taken to find patterns (Boyer-Moore) for 'beef': " + elapsedTime + " milliseconds");

        // Add your assertions here based on your requirements
        assertNotNull(foundRecipes); // Check if the result is not null
        // Add more assertions as needed
        for (Recipe recipe : foundRecipes) {
            assertTrue(recipe.getTitle().toLowerCase().contains(pattern)); // Ensure each found recipe contains the title "beef"
        }
    }


    @Test
    public void testAddNewUser() {
        User user = new User(1L, "Alicee", new ArrayList<Long>());
        boolean saved = recipeService.getUserDao().save(user);
        assertTrue(saved);

        User retrievedUser = recipeService.getUserDao().find(1L);
        assertNotNull(retrievedUser);
        assertEquals("Alicee", retrievedUser.getName());
    }
}