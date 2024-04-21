package org.example.storeclient.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private Long id;
    private String name;
    private List<Long> recipeIds = new ArrayList<>();

    public User() {}

    public User(Long id, String name, List<Long> recipeIds) {
        this.id = id;
        this.name = name;
        this.recipeIds = recipeIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getRecipeIds() {
        return recipeIds;
    }

    public void setRecipeIds(List<Long> recipeIds) {
        this.recipeIds = recipeIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean addRecipe(Recipe recipe) {
        return recipeIds.add(recipe.getId());
    }
}