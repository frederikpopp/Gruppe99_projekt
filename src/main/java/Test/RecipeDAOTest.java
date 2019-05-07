package Test;

import Recipe.*;
import Recipe.RecipeDAO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

class RecipeDAOTest {

    IRecipeDAO recipeDAO = new RecipeDAO();

    void jUnitRecipe(){
        try {
            RecipeDTO testRecipe = new RecipeDTO();
            testRecipe.setRecipeID(23);
            testRecipe.setRecipeName("Norethisteron");
            testRecipe.setManufacturer("PantherPharma");

            List<IRecipeContentsDTO> ingredientsList = new ArrayList<>();
            RecipeContentsDTO Estradiol = new RecipeContentsDTO();
            RecipeContentsDTO Norethisteronacetat = new RecipeContentsDTO();

            RecipeContentsDTO Opovidon = new RecipeContentsDTO();
            RecipeContentsDTO Laktosemonohydrat = new RecipeContentsDTO();
            RecipeContentsDTO Magnesiumstearat = new RecipeContentsDTO();
            RecipeContentsDTO Majsstivelse = new RecipeContentsDTO();

            Estradiol.setIngredientID(12);
            Estradiol.setAmount(1.0);
            Estradiol.setUseCase("Active");

            Norethisteronacetat.setIngredientID(13);
            Norethisteronacetat.setAmount(0.5);
            Norethisteronacetat.setUseCase("Active");

            Opovidon.setIngredientID(14);
            Opovidon.setAmount(50);
            Opovidon.setUseCase("Tablet");

            Laktosemonohydrat.setIngredientID(15);
            Laktosemonohydrat.setAmount(10);
            Laktosemonohydrat.setUseCase("Tablet");

            Magnesiumstearat.setIngredientID(4);
            Magnesiumstearat.setAmount(15);
            Magnesiumstearat.setUseCase("Tablet");

            Majsstivelse.setIngredientID(16);
            Majsstivelse.setAmount(120);
            Majsstivelse.setUseCase("Tablet");

            ingredientsList.add(Estradiol);
            ingredientsList.add(Norethisteronacetat);
            ingredientsList.add(Opovidon);
            ingredientsList.add(Laktosemonohydrat);
            ingredientsList.add(Magnesiumstearat);
            ingredientsList.add(Majsstivelse);

            testRecipe.setIngredients(ingredientsList);

            recipeDAO.createRecipe(testRecipe);

            IRecipeDTO receivedRecipe = new recipeDAO.getRecipe(23);

            assertEquals(testRecipe.getRecipeID(), receivedRecipe.getRecipeID());
            assertEquals(testRecipe.getRecipeName(), receivedRecipe.getRecipeName());
            assertEquals(testRecipe.getManufacturer(), receivedRecipe.getManufacturer());
            assertEquals(testRecipe.getIngredients(), receivedRecipe.getIngredients());


            recipeDAO.removeRecipe(23);


        } catch (Utilities.DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }



}