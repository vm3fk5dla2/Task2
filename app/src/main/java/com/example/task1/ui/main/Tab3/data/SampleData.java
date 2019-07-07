package com.example.task1.ui.main.Tab3.data;

import com.example.task1.ui.main.Tab3.model.RecipeList;

import java.util.ArrayList;

public class SampleData  {

    ArrayList<RecipeList> recipes = new ArrayList<>();

    public ArrayList<RecipeList> getRecipes() {

        RecipeList menu1 = new RecipeList("김치찌개");

        recipes.add(menu1);

        RecipeList menu2 = new RecipeList("김치찌개");

        recipes.add(menu2);

        RecipeList menu3 = new RecipeList("김치찌개");

        recipes.add(menu3);

        RecipeList menu4 = new RecipeList("김치찌개");

        recipes.add(menu4);

        RecipeList menu5 = new RecipeList("김치찌개");

        recipes.add(menu5);

        RecipeList menu6 = new RecipeList("김치찌개");

        recipes.add(menu6);

        return recipes;
    }
}
